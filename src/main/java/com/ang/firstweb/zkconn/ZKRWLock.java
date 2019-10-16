package com.ang.firstweb.zkconn;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * Created by adimn on 2019/10/15.
 */
public class ZKRWLock implements IZkChildListener {
    private final ZkClient zkClient;
    private String path;
    private String nodepath = "nodepath";
    private CountDownLatch countDownLatch;
    private String currentNode;
    private String host = "127.0.0.1:2181";

    public ZKRWLock(String host, String path) {
        if (host != null) {
            this.host = host;
        }
        zkClient = new ZkClient(this.host, 3000);
        this.path = path;
        countDownLatch = new CountDownLatch(1);
        if (!zkClient.exists(path)) {
            zkClient.createPersistent(path);
        }
    }

    public void lock(Boolean exclusion) {
        currentNode = zkClient.createEphemeralSequential(path + "/" + nodepath, exclusion);
        List<String> collect = zkClient.getChildren(path);
        if (isFirstChild(currentNode, collect)) {
            return;
        }
        try {
            zkClient.subscribeChildChanges(path, this);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void unlock() {
        if (zkClient.exists(currentNode)) {
            zkClient.delete(currentNode);
        }
    }


    @Override
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
        System.out.println(parentPath +"--"+currentChilds.toString());
        if (isFirstChild(currentNode, currentChilds)) {
            countDownLatch.countDown();
        }
        zkClient.subscribeChildChanges(path, this);
    }

    private boolean isFirstChild(String current, List<String> all) {
        String[] split = current.split("/");
        List<String> collect = all.stream().sorted().collect(Collectors.toList());
        int index = collect.indexOf(split[split.length-1]);
        if (index == 0) {
            return true;
        }
        return false;
    }
}
