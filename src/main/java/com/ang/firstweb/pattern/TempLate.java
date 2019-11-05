package com.ang.firstweb.pattern;

/**
 * Created by adimn on 2019/11/5.
 */
public abstract class TempLate {
    public void pre(){
        System.out.println("i am pre");
    }
    abstract void methodA();

    public void methodB(){
        System.out.println("i am b");
    }

    public void dosth(){
        pre();
        methodA();
        methodB();
    }
}
