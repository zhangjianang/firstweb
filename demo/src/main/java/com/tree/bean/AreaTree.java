package com.tree.bean;



import com.tree.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adimn on 2018/12/20.
 */
public class AreaTree extends Tree {

    private int level;
    private Long id;
    private String text;
    private List<AreaTree> children;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AreaTree> getChildren() {
        return children;
    }

    public void setChildren(List<AreaTree> children) {
        this.children = children;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public List<Tree> getChildList() {
        List<Tree> res =new ArrayList<Tree>();
        for(Tree per:getChildren()){
            res.add(per);
        }
        return res;
    }

    @Override
    public void accept(Visitor visitor, Long startId) {
        visitor.visit(this,startId);
    }


}
