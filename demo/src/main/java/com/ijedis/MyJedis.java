package com.ijedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * Created by adimn on 2019/10/16.
 */
public class MyJedis {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000);
        Jedis jedis = jedisPool.getResource();

//        System.out.println(jedis.set("ang", "hello"));
//        System.out.println(jedis.get("ang"));
//
//        System.out.println(jedis.bitpos("ang", false));
//        jedis.mset("ang", "1", "lily", "10", "ang3", "14");
//        jedis.mget("ang", "lily", "ang3").stream().forEach(p -> System.out.println(p));

//        System.out.println(jedis.lpush("list", "1", "2"));
//        for (int i = 0; i < 3; i++) {
//            jedis.blpop(10, "list").stream().forEach(System.out::println);
//        }
//        System.out.println(jedis.setnx("ang", "sth"));
//        Pipeline pl = jedis.pipelined();
//        pl.setnx("ang2","bb");
//        pl.get("ang2");
//        pl.syncAndReturnAll().stream().forEach(System.out::println);



    }
    public static void jexpire(Jedis jedis){
        jedis.expire("ang",10);
        for(int i=0;i<3;i++) {
            System.out.println(jedis.get("ang"));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void multiget(){

    }
}
