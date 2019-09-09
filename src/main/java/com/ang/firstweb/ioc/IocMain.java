package com.ang.firstweb.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by adimn on 2019/8/30.
 */
public class IocMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application-bean.xml");
//        AngHello bean = context.getBean(AngHello.class);

//        FactoryBean  常用来集成第三方
        System.out.println(context.getBean("mydriver").getClass());
        ConcurrentLinkedDeque queue = new ConcurrentLinkedDeque();
    }
}
