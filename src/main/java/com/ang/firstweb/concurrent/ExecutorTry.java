package com.ang.firstweb.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by adimn on 2019/8/22.
 */
public class ExecutorTry {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new LinkedBlockingDeque(1);
        Executor executor = new ThreadPoolExecutor(3,4,100, TimeUnit.MILLISECONDS,blockingQueue);

        List l1 = new ArrayList<Runnable>(5);
        for(int i=0;i<5;i++) {

            Thread t = new Thread(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" start ");
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.setName("mythread-"+i);
            l1.add(t);

        }

        for(int i=0;i<l1.size();i++) {
            executor.execute((Runnable) l1.get(i));
            System.out.println("size:"+blockingQueue.size());
        }
    }

    public static void blockqueueTest() {
        BlockingQueue queue = new LinkedBlockingDeque(5);
        queue.add(new Object());
        System.out.println(queue.poll());
    }

}

class User{
    private String name;

}
