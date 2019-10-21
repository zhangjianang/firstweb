package com.ang.firstweb.treetravel;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by adimn on 2019/10/21.
 */
public class TreeTravel {
    public static void main(String[] args) {
        int[] nums = {1, 3, 7, 5, 4, 6, 2, 9};
        TreeNode root = new TreeNode(nums[0]);
        TreeNode originroot = root;
        for (int i = 1; i < nums.length; i++) {
            TreeNode per = new TreeNode(nums[i]);
            if (i % 2 == 0) {
                root.right = per;
                root = root.left;
            } else {
                root.left = per;
            }
        }

        showTree("pre", originroot);
        showTree("pret",originroot);
        showTree("mid", originroot);
        showTree("post", originroot);
    }

    public static void showTree(String type, TreeNode root) {
        List res = new ArrayList();
        if ("pre".equals(type)) {
            preTravel(root, res);
        } else if ("mid".equals(type)) {
            midTravel(root, res);
        } else if ("post".equals(type)) {
            postTravel(root, res);
        }else if("pret".equals(type)){
            preLoop(root,res);
        }

        res.stream().forEach(p -> System.out.print(p + " "));
        System.out.println();
    }

    public static void preTravel(TreeNode node, List<Integer> visits) {
        if (node == null) {
            return;
        }
        visits.add(node.num);
        preTravel(node.left, visits);
        preTravel(node.right, visits);
    }

    public static void midTravel(TreeNode node, List<Integer> visits) {
        if (node == null) {
            return;
        }
        midTravel(node.left, visits);
        visits.add(node.num);
        midTravel(node.right, visits);
    }

    public static void postTravel(TreeNode node, List<Integer> visits) {
        if (node == null) {
            return;
        }
        postTravel(node.left, visits);
        postTravel(node.right, visits);
        visits.add(node.num);
    }

    public static void preLoop(TreeNode node, List<Integer> visits) {
        Stack stack = new Stack();
        stack.push(node);

        while (stack.size()>0) {
            TreeNode cur = (TreeNode) stack.pop();
            visits.add(cur.num);
            while (cur.left!=null){
                stack.push(cur.left);
                cur = cur.left;
            }
            while (cur.right!=null){
                stack.push(cur.right);
                cur = cur.right;
            }
        }
    }

}
