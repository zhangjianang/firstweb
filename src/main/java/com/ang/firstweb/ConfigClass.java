package com.ang.firstweb;

import com.ang.firstweb.aop.problem.MyBusiness;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by adimn on 2019/8/27.
 */
@Configuration
//@ImportResource(locations="classpath:application-bean.xml")
@ImportResource(locations="classpath:bean-aop.xml")
public class ConfigClass {
    @Bean
    public MyBusiness myBusiness(){
        return new MyBusiness();
    }
}
