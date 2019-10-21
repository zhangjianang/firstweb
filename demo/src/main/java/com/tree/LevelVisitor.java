package com.tree;



import com.tree.bean.Tree;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adimn on 2018/12/20.
 */
@Component
public class LevelVisitor implements Visitor {

    private List<Long> ids;

    public LevelVisitor(){
        ids = new ArrayList<Long>();
    }

    @Override
    public  void visit(Tree tree, Long startId) {
        Long treeId = tree.getId();
        Boolean fatherEqual = false;
        if(treeId >0 && startId.compareTo(treeId)==0) {
            ids.add(treeId);
//            如果父节点相等，则他下面的所有子都要返回。
            fatherEqual = true;
        }

        if(tree.getChildList().size()>0){
            for(Tree per :tree.getChildList()){
                if(fatherEqual) {
                    per.accept(this, per.getId());
                }else {
                    per.accept(this, startId);
                }
            }
        }
    }

    public List<Long> getIds() {
        List<Long> resIds = new ArrayList<Long>();
        resIds.addAll(ids);
        ids.clear();
        return resIds;
    }
}
