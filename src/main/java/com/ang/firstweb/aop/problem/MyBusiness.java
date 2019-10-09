package com.ang.firstweb.aop.problem;


import org.springframework.aop.framework.AopContext;
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
        ((MyBusiness)AopContext.currentProxy()).methodB();
    }

    public void methodB(){
        System.out.println("i am B");
    }
}
