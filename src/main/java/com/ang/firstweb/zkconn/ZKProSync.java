package com.ang.firstweb.zkconn;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by adimn on 2019/8/26.
 */
public class ZKProSync implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        //zookeeper配置数据存放路径
        String path = "/ang";
        //连接zookeeper并且注册一个默认的监听器
        zk = new ZooKeeper("localhost:2181", 5000,new ZKProSync());
        //等待zk连接成功的通知
        connectedSemaphore.await();
        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println(new String(zk.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);

    }



    @Override
    public void process(WatchedEvent event) {
        //zk连接成功通知事件
        if (Event.KeeperState.SyncConnected == event.getState()) {
            //如果是刚刚连接成功
            Event.EventType type = event.getType();
            if (Event.EventType.None == type && null == event.getPath()) {
                connectedSemaphore.countDown();
                //zk目录节点数据变化通知事件
            } else if (type == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {

                }
            }else if (type ==  Event.EventType.NodeDeleted){

            }
        }
    }
}
