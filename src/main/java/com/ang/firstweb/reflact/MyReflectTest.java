package com.ang.firstweb.reflact;

import java.lang.reflect.Method;

/**
 * Created by adimn on 2019/9/6.
 */
public class MyReflectTest {

    public static void main(String[] args) {
        MyBean myBean = new MyBean();
        myBean.setName("ang");
        myBean.setDelFlag(0);
        genInfo(myBean);
        System.out.println(MyBean.class.toString());

    }
    public static void genInfo(Object bean) {
        Class<?> clazz = bean.getClass();
        try {
            Method setDelFlag = clazz.getMethod("setDelFlag1", Integer.class);
            setDelFlag.invoke(bean, 1);
        }catch (Exception e ){
            e.printStackTrace();
        }
        System.out.println(bean.toString());
    }

    public static String checkFinally(){
        try{
            return "i am try will not return";
        }finally {
            return "finally";
        }
    }
}
