package com.ang.firstweb.aop.problem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by adimn on 2019/9/6.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyAspectTest {

    @Autowired
    MyBusiness myBusiness;

    @Test
    public void myaoptest() {
        ((MyBusiness) AopContext.currentProxy()).methodA();

    }
}