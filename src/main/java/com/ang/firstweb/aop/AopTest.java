package com.ang.firstweb.aop;

import com.ang.firstweb.ConfigClass;
import com.ang.firstweb.aop.problem.MyBusiness;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by adimn on 2019/9/5.
 */
public class AopTest {
    public static void main(String[] args) {
//        DefaultAopProxyFactory  决定是否采用jdk代理还是CGLIB动态代理
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
//        context.refresh();
            MyBusiness bean = context.getBean(MyBusiness.class);
            bean.methodA();
        }
}
