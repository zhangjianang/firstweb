package com.ang.firstweb.reflact;

/**
 * Created by adimn on 2019/9/6.
 */
public class MyBean {
    private Integer delFlag;
    private String name;

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "delFlag=" + delFlag +
                ", name='" + name + '\'' +
                '}';
    }
}
