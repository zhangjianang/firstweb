package com.ang.firstweb.service.impl;

import com.ang.firstweb.service.UserService;

/**
 * Created by adimn on 2019/8/27.
 */
public class UserServiceImpl implements UserService {
    private int port;

    @Override
    public String getInfo() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return toString();
    }

    @Override
    public void doNothing() {
        try {
            Thread.sleep(1000);
            System.out.println("we do nothing");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "port=" + port +
                '}';
    }
}
