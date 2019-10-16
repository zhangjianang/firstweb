package com.ang.firstweb.zkconn;

/**
 * Created by adimn on 2019/10/15.
 */
public class ZKRWLockTest {
    private static Integer num = 0;

    public static void main(String[] args) throws InterruptedException {

        ZKRWLock lock = new ZKRWLock(null, "/lock");
        Thread.sleep(3000);
        System.out.println(num+" 抢锁 ");
        lock.lock(true);
        System.out.println(num+" 获取锁 ");
        Thread.sleep(15000);
        System.out.println(num+" do sth ");
        lock.unlock();
    }
}
