package com.ang.firstweb.zkconn;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by adimn on 2019/8/26.
 */
public class CountDownDemo implements Runnable{


        static final CountDownLatch latch = new CountDownLatch(10);
        static final CountDownDemo demo = new CountDownDemo();

        @Override
        public void run() {
            // 模拟检查任务
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println("check complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //计数减一
                //放在finally避免任务执行过程出现异常，导致countDown()不能被执行
                latch.countDown();
            }
        }


        public static void main(String[] args) throws InterruptedException {
            ExecutorService exec = Executors.newFixedThreadPool(10);
            for (int i=0; i<10; i++){
                exec.submit(demo);
            }

            // 等待检查
//            latch.await();

            // 发射火箭
            System.out.println("Fire!");
            // 关闭线程池
            exec.shutdown();
        }
}
