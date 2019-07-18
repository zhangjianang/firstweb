package com.ang.firstweb.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by adimn on 2019/7/9.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Integer co_id;
    private String co_name;
    private Integer is_target;


    public Course(){

    }

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public Integer getIs_target() {
        return is_target;
    }

    public void setIs_target(Integer is_target) {
        this.is_target = is_target;
    }
}
