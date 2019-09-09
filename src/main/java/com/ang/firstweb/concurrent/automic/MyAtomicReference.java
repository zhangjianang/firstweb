package com.ang.firstweb.concurrent.automic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by adimn on 2019/9/4.
 */
public class MyAtomicReference {
    public static void main(String[] args) {
        int[] value = new int[]{1,2};
        AtomicIntegerArray array = new AtomicIntegerArray(value);
        int res = array.getAndSet(0,3);
        if(res == value[0]){
            System.out.println("相等: "+res);
            System.out.println("替换之后: "+array.get(0));
        }
    }
}
