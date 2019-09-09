package com.ang.firstweb.aop.problem;


import org.springframework.stereotype.Component;

/**
 * Created by adimn on 2019/9/6.
 */
@Component
public class MyBusiness {


    public void methodA(){
        System.out.println("i am A");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        methodB();
    }

    public void methodB(){
        System.out.println("i am B");
    }
}
