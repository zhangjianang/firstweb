package com.ang.firstweb.zkconn;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.io.IOException;
import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by adimn on 2019/10/14.
 */
public class ZKconn  {
    private static String conn = "127.0.0.1:2181";
    private static ZooKeeper zk;

    public static void main(String[] args) {

        try {
            zk = new ZooKeeper(conn, 1000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("类型:"+event.getType());
                    System.out.println("stat:"+event.getState());
                    Event.EventType type = event.getType();
                    if(type == Event.EventType.None){
                        System.out.println("连接成功！");
                    }
                    if (type == Event.EventType.NodeCreated){
                        System.out.println("创建新的");
                    }

                }
            });

            List<ACL>  acls = new ArrayList<>(3);
            int perm = ZooDefs.Perms.ADMIN | ZooDefs.Perms.READ;
            ACL acl = new ACL(perm, new Id("world", "anyone"));
            acls.add(acl);
            zk.create("/remote", "ha".getBytes(), acls, CreateMode.PERSISTENT);




        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }


}
