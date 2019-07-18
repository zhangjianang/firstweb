package com.ang.firstweb.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by adimn on 2019/7/9.
 */
@ConfigurationProperties(prefix="girl")
@Component
public class GirlProperties {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name : "+name+" , age : "+age;
    }
}
