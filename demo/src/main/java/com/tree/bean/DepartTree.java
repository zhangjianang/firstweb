package com.tree.bean;






import com.tree.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2018/12/20.
 */
public class DepartTree extends Tree{

    private Long ID;
    private String name;
    private List<Map> menbers;
    private List<DepartTree> childDepartmentList;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Map> getMenbers() {
        return menbers;
    }

    public void setMenbers(List<Map> menbers) {
        this.menbers = menbers;
    }

    public List<DepartTree> getChildDepartmentList() {
        return childDepartmentList;
    }

    public void setChildDepartmentList(List<DepartTree> childDepartmentList) {
        this.childDepartmentList = childDepartmentList;
    }

    @Override
    public String toString() {
        return "DepartTree{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", menbers=" + menbers +
                ", childDepartmentList=" + childDepartmentList +
                '}';
    }


    @Override
    public void accept(Visitor visitor, Long sartId) {
        visitor.visit(this,sartId);
    }

    @Override
    public Long getId() {
        return this.getID();
    }

    @Override
    public List<Tree> getChildList() {
        List<Tree>  res = new ArrayList<Tree>();
        for(Tree per:getChildDepartmentList()){
            res.add(per);
        }
        return res;
    }
}

