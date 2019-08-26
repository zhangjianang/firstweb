package com.ang.firstweb.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by adimn on 2019/8/26.
 */
public class ClientWatcher implements IZkDataListener{

    private ZkClient zkClient;
    private String path;

    public ClientWatcher(ZkClient zkClient, String path) {
        this.zkClient = zkClient;
        this.path = path;
    }

    @Override
    public void handleDataChange(String s, Object o) throws Exception {
        System.out.println("观察到："+s+" , 改变："+o);
        zkClient.subscribeDataChanges(path,this);
    }

    @Override
    public void handleDataDeleted(String s) throws Exception {
        System.out.println("删除了："+s);
    }
}
