package com.tree;


import com.tree.bean.Tree;

/**
 * Created by adimn on 2018/12/20.
 */
public interface Visitor {
    void visit(Tree tree, Long startId);
}
