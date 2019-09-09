package com.ang.firstweb.aop;

/**
 * Created by adimn on 2019/9/6.
 */
public interface Dog {
    void info();
    void run();
}

class RunDog implements  Dog{

    @Override
    public void info() {
        System.out.println(" i am  run dog");
    }

    @Override
    public void run() {
        System.out.println(" running all day");
    }
}

class DogUtil{
    public void before(){
        System.out.println(" 模拟 before ");
    }

    public void after(){
        System.out.println(" 模拟 after ");
    }
}
