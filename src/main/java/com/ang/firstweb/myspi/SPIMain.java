package com.ang.firstweb.myspi;

import java.util.ServiceLoader;

/**
 * Created by adimn on 2019/8/29.
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> service = ServiceLoader.load(IShout.class);
        for(IShout per:service){
            per.shout();
        }
    }
}
