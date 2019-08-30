package com.ang.firstweb.ioc;

import org.springframework.beans.factory.FactoryBean;

import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created by adimn on 2019/8/30.
 */
public class MyDriverFactoryBean implements FactoryBean {
    private String url ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Object getObject() throws Exception {
        return DriverManager.getDriver(url);
    }

    @Override
    public Class<?> getObjectType() {
        return Driver.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void init() {
        System.out.println("this is init");
    }
}
