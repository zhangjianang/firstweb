package com.ang.firstweb.zkclient;

import java.io.Serializable;

/**
 * Created by adimn on 2019/8/26.
 */
public class ZKUser implements Serializable{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ZKUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
