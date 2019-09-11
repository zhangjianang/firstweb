package com.ang.firstweb.transaction;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by ang on 2019/9/12.
 */
public class MyAnnoTranTest {
    @Test
    public void add() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        MyAnnoTran bean = context.getBean(MyAnnoTran.class);
        bean.add();
    }

}