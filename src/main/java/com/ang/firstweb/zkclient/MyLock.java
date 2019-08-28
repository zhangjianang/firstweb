package com.ang.firstweb.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

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

    public void lock() {
        if(try2Lock()){
            System.out.println(current+" 获得锁");
        }else{

            zkClient.subscribeChildChanges(PATH,this);
            countDownLatch = new CountDownLatch(1);
            System.out.println(current+" 获取失败，等待");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock(){
        zkClient.delete(current);
        System.out.println(current+" 删除成功");
    }

    private Boolean try2Lock() {
        byte[] address = new byte[0];
        try {
            address = InetAddress.getLocalHost().getAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String sub = "node";
        current =  zkClient.createEphemeralSequential(PATH+"/"+sub, address.toString());
        return checkIsMin();
    }

    public Boolean checkIsMin(){

        String sub = "node";
        List<String> children = zkClient.getChildren(PATH);

        Integer min = Integer.MAX_VALUE;
        for(String per:children){
            int curint = Integer.parseInt(per.substring(sub.length()));
            if(curint<min){
                min = curint;
            }
        }

        if(Integer.parseInt(current.substring((PATH+"/"+sub).length())) == min){
            return true;
        }
        return false;
    }


    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        if(checkIsMin()){
            countDownLatch.countDown();
            System.out.println(current+" 唤醒成功");
        }else {
            zkClient.subscribeChildChanges(PATH, this);
            System.out.println(current+" 唤醒失败，继续等待");
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    MyLock myLock = new MyLock();
                    myLock.lock();
                    System.out.println("进行处理");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myLock.unlock();
                }
            }.start();
        }
    }
}
