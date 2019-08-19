package com.ang.firstweb.myclassloader;

/**
 * Created by ang on 2019/8/18.
 */
public class GCTry {
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation,allocation2,allocation3;
        allocation = new byte[28000*1024];
        allocation2 = new byte[28000*1024];
        allocation3   = new byte[28000*1024];

        Thread.sleep(Integer.MAX_VALUE);

    }
}
