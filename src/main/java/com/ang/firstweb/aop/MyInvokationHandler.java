package com.ang.firstweb.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by adimn on 2019/9/6.
 */
public class MyInvokationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("正在执行的 方法："+method);
        if(args !=null){
            System.out.println("闯入参数：");
            for(Object arg:args){
                System.out.println(arg);
            }
        }else{
            System.out.println("没有参数！");
        }
        return null;
    }

    public static void main(String[] args) {
        InvocationHandler invocationHandler = new MyInvokationHandler();
        Person per = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, invocationHandler);
        per.sayHello();
        per.walk();
    }
}
