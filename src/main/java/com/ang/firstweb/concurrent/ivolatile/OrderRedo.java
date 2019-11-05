package com.ang.firstweb.concurrent.ivolatile;

import sun.misc.Unsafe;

/**
 * Created by adimn on 2019/11/5.
 */
public class OrderRedo {
    private static int x, y, a, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i= 0;
        for (; ; ) {
            i++;
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    MyUnsafeOption.getUnsafe();
                    x = b;
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    MyUnsafeOption.getUnsafe();
                    y = a;
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("第 "+i+" 次:( x = "+x+",y = "+y+")");
            if(x ==0&& y==0){
                System.out.println("0,0 happened");
                break;
            }
        }
    }
}
