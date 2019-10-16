package com.ang.firstweb.zkconn;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by adimn on 2019/10/16.
 */
public class ZkDistribute implements IZkDataListener {
    private final CountDownLatch countDownLatch;
    private ZkClient zkClient;
    private String path = "/single";
    private String node = "/node";
    private String host = "127.0.0.1:2181";
    private String current;


    public ZkDistribute() {

        zkClient = new ZkClient(host, 3000);
        countDownLatch = new CountDownLatch(1);
        if (!zkClient.exists(path)) {
            zkClient.createPersistent(path, "h");
        }
    }

    public void trytostart() throws InterruptedException {
        current = zkClient.createEphemeralSequential(path + node, false);
        List<String> children = zkClient.getChildren(path).stream().sorted().collect(Collectors.toList());
        int cindex = isFirstChild(children);
        if (cindex == 0) {
            zkClient.writeData(current, true);
            System.out.println(current + " 被选为主");
        } else {
            zkClient.subscribeDataChanges(path + "/" + children.get(cindex - 1), this);
            System.out.println(current + " 被阻塞");
            countDownLatch.await();
        }
    }

    private int isFirstChild(List<String> children) {
        if (current == null) {
            return -1;
        }
        String[] split = current.split("/");
        return children.indexOf(split[split.length - 1]);
    }

    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {

    }

    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        System.out.println(current + " 监听param :" + dataPath);
        List<String> children = zkClient.getChildren(path).stream().sorted().collect(Collectors.toList());
        int index = isFirstChild(children);
        if (index == 0) {
            zkClient.writeData(current, true);
            countDownLatch.countDown();
            System.out.println(current + "获取锁了");
        } else {
            System.out.println(current + "继续监听");
            zkClient.subscribeDataChanges(path + "/" + children.get(index - 1), this);
        }
    }

    public static void main(String[] args) {
        ZkDistribute zkDistribute = new ZkDistribute();
        try {
            Thread.sleep(15000);
            zkDistribute.trytostart();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
