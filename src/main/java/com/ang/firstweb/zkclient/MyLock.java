package com.ang.firstweb.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by adimn on 2019/8/26.
 */
public class MyLock implements IZkChildListener{

    private ZkClient zkClient = new ZkClient("localhost:2181");
    private static final String PATH = "/test";
    private String  current;
    private CountDownLatch countDownLatch ;

    public void lock() throws UnknownHostException, InterruptedException {
        if(try2Lock()){

        }else{
            zkClient.subscribeChildChanges(PATH,this);
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        }
    }

    public void unlock(){
        zkClient.delete(PATH+"/"+current);
    }

    private Boolean try2Lock() throws UnknownHostException {
        byte[] address = InetAddress.getLocalHost().getAddress();
        String sub = "node";
        current =  zkClient.createEphemeralSequential(PATH+"/"+sub, address.toString());

        List<String> children = zkClient.getChildren(PATH);

        Integer min = Integer.MAX_VALUE;
        for(String per:children){
            int curint = Integer.parseInt(per.substring(sub.length()));
            if(curint<min){
                min = curint;
            }
        }

        if(Integer.parseInt(current.substring(sub.length())) == min){
            return true;
        }
        return false;
    }


    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        if(try2Lock()){
            countDownLatch.countDown();
        }
    }
}
