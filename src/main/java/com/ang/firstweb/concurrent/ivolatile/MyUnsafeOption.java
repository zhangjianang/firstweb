package com.ang.firstweb.concurrent.ivolatile;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by adimn on 2019/11/5.
 */
public class MyUnsafeOption {
    public static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);

            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
