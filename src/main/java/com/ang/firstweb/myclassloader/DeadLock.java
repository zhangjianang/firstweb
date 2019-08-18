package com.ang.firstweb.myclassloader;

/**
 * Created by ang on 2019/8/17.
 */
public class DeadLock {

    public static void main(String[] args) {
        Object ob1 = new Object();
        Object ob2 = new Object();

        new Thread(){
            public void run(){
                synchronized (ob1){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (ob2) {
                        System.out.println("1 in");
                    }
                }
            }
        }.start();

        new Thread(){
            public void run(){
                synchronized (ob2){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (ob1) {
                        System.out.println("2 in");
                    }
                }
            }
        }.start();
    }
}
