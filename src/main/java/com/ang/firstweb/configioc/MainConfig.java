package com.ang.firstweb.configioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

/**
 * Created by ang on 2019/9/8.
 */
@Configuration
@ComponentScan(basePackages = {"com.ang.firstweb.concurrent"},excludeFilters = {})
//@Import(value = {ImportClass.class})
public class MainConfig {
    @Bean
    public Person person2(){
        return new Person();
    }

    @Bean
    @Conditional(value = AngCondition.class)
    public AngConditionLog angCondition(){
        return new AngConditionLog();
    }

    @Bean(initMethod = "init")
    public Car car(){
        return new Car();
    }

    @Bean
    @Profile(value = "dev")
    public ProfileConn profileConn(){
        return new ProfileConn("dev");
    }

    @Bean
    @Profile(value = "test")
    public ProfileConn profileConnTest(){
        return new ProfileConn("test");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(MainConfig.class);
        context.refresh();
        for(String beanName:context.getBeanDefinitionNames()){
            System.out.println(beanName);
        }
    }
}
