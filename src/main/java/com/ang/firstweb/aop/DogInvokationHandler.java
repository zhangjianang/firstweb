package com.ang.firstweb.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by adimn on 2019/9/6.
 */
public class DogInvokationHandler implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil dogUtil = new DogUtil();
        dogUtil.before();
        Object invoke = method.invoke(target, args);
        dogUtil.after();
        return invoke;
    }
}
