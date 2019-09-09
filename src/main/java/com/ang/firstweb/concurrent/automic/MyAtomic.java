package com.ang.firstweb.concurrent.automic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by adimn on 2019/9/4.
 */
public class MyAtomic {

    public static void main(String[] args) {
//        autoInc();
//        abaProblem();
        abaProblemSolve();
    }


    public static void autoInc() {
        AtomicInteger automicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread tt = new Thread() {
                @Override
                public void run() {

                    automicInteger.incrementAndGet();
                    countDownLatch.countDown();
                }
            };
            tt.start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("add ten:" + automicInteger.get());
    }


    public static void abaProblem() {
        AtomicInteger aint = new AtomicInteger(0);
        Thread main = new Thread("main") {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean succ = aint.compareAndSet(0, 10);
                if (succ) {
                    System.out.println("成功设置值");
                } else {
                    System.out.println("sth is wrong");
                }
            }
        };

        Thread trouble = new Thread("trouble") {
            @Override
            public void run() {
                int inc = aint.incrementAndGet();
                System.out.println("增加之后：" + inc);
                int des = aint.decrementAndGet();
                System.out.println("减少之后：" + des);
            }
        };
        main.start();
        trouble.start();

    }

    public static void abaProblemSolve() {
        AtomicStampedReference<Integer> asint = new AtomicStampedReference<>(0, 0);
        Thread main = new Thread(() -> {
            int stamp = asint.getStamp();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean succ = asint.compareAndSet(0, 10, stamp, stamp + 1);
            if (succ) {
                System.out.println("成功设置值");
            } else {
                System.out.println("sth is wrong");
            }
        }, "main");

        Thread trouble = new Thread("trouble") {
            @Override
            public void run() {
                int stamp = asint.getStamp();
                System.out.println("增加之后：" + asint.compareAndSet(0,1,stamp,stamp+1));
                stamp = asint.getStamp();
                System.out.println("减少之后：" + asint.compareAndSet(1,0,stamp,stamp+1));
            }
        };
        main.start();
        trouble.start();
    }
}
