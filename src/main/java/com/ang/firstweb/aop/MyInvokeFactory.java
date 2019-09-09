package com.ang.firstweb.aop;

import java.lang.reflect.Proxy;

/**
 * Created by adimn on 2019/9/6.
 */
public class MyInvokeFactory {
    public static Object getProxy(Object target){
        DogInvokationHandler dogInvokationHandler = new DogInvokationHandler();
        dogInvokationHandler.setTarget(target);
        Dog dog = (Dog)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), dogInvokationHandler);
        return dog;
    }

    public static void main(String[] args) {
        Dog runDog = new RunDog();

        Dog proxy = (Dog)MyInvokeFactory.getProxy(runDog);
        proxy.info();
        proxy.run();
    }
}
