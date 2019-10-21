package com.tree.bean;


import java.util.List;

/**
 * Created by adimn on 2018/12/20.
 */
public abstract class Tree implements Element {
    public abstract Long getId();
    public abstract List<Tree> getChildList();
}
