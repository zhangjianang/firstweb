package com.myagent;

import java.lang.instrument.Instrumentation;

/**
 * Created by adimn on 2019/9/26.
 */
public class AgentMain2 {
    public static void main(String[] args) {
//        System.out.println("hello every one !");
        StringUtils stringUtils = new StringUtils();
        System.out.println("结果是："+stringUtils.addStringTime(10));
    }
}
