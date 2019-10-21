package com.tree.bean;


import com.tree.Visitor;

/**
 * Created by adimn on 2018/12/20.
 */
public interface  Element {
    void accept(Visitor visitor, Long startId);
}
