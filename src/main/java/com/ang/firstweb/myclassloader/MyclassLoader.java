package com.ang.firstweb.myclassloader;

/**
 * Created by ang on 2019/8/17.
 */
public class MyclassLoader {
    public static void main(String[] args) {
        System.out.println(java.util.Random.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(MyclassLoader.class.getClassLoader().getClass().getName());
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());


        System.out.println("-------------------");
        new A();
        System.out.println("sht");
        new B();
    }
}

class A{

}

class B{

}
