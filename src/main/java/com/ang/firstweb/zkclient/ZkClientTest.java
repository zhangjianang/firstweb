package com.ang.firstweb.zkclient;

import org.I0Itec.zkclient.ZkClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by adimn on 2019/8/26.
 */
public class ZkClientTest {
    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        String connStr = "localhost:2181";
//        支持序列化
//        ZkClient zkClient = new ZkClient(connStr,500,500,new SerializableSerializer());

//        简单的只用
        ZkClient zkClient = new ZkClient(connStr);
        zkClient.setZkSerializer(new MySerializer());
//        addAng();

//      watchInfo(zkClient);
        for(int i=0;i<10;i++) {
            String cres = zkClient.createEphemeralSequential("/test/node", InetAddress.getLocalHost().getAddress().toString());
            System.out.println("创建：" + cres);
        }
        List<String> children = zkClient.getChildren("/test");
        System.out.println(children.get(0).toString());

    }



    public static void watchInfo(ZkClient zkClient)  {
        ClientWatcher myWatcher = new ClientWatcher(zkClient,"/ang");
        zkClient.subscribeDataChanges("/ang",myWatcher);

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ZKUser zkUser = new ZKUser();
//        zkUser.setName("莉莉");
//        zkUser.setAge(15);
//        zkClient.writeData("/ang",zkUser);
    }

    public static void addUser(ZkClient zkClient) {
        ZKUser zkUser = new ZKUser();
        zkUser.setName("zhang");
        zkUser.setAge(10);

        zkClient.createPersistent("/ang",zkUser);
    }



}
