package com.ang.firstweb.treetravel;

import javax.xml.soap.Node;
import java.util.*;

/**
 * Created by adimn on 2019/10/21.
 */
public class TreeTravel {
    public static void main(String[] args) {
//        int[] nums = {1, 3, 7, 5, 4, 6, 2, 9};
//        TreeNode root = new TreeNode(nums[0]);
//        TreeNode originroot = root;
//        for (int i = 1; i < nums.length; i++) {
//            TreeNode per = new TreeNode(nums[i]);
//            if (i % 2 == 0) {
//                root.right = per;
//                root = root.left;
//            } else {
//                root.left = per;
//            }
//        }
        TreeNode originroot = stringToTreeNode("[1, 3, 7, 5, 4, 6, 2, 9]");

//        showTree("pre", originroot);
//        showTree("pret", originroot);
        showTree("mid", originroot);
        showTree("midt", originroot);
//        showTree("post", originroot);
//        showTree("postt", originroot);
    }

    public static void showTree(String type, TreeNode root) {
        List res = new ArrayList();
        if ("pre".equals(type)) {
            preTravel(root, res);
        } else if ("mid".equals(type)) {
            midTravel(root, res);
        } else if ("post".equals(type)) {
            postTravel(root, res);
        } else if ("pret".equals(type)) {
            preLoop(root, res);
        } else if ("midt".equals(type)) {
            midloop(root, res);
        } else if ("postt".equals(type)) {
            postloop(root, res);
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

        while (stack.size() > 0) {
            TreeNode cur = (TreeNode) stack.pop();
            visits.add(cur.num);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public static void midloop(TreeNode node, List<Integer> visits) {
        Stack stack = new Stack();

        TreeNode cur = node;
        while (!stack.empty() || cur != null) {
            while (cur!= null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur =(TreeNode) stack.pop();
            visits.add(cur.num);
            cur = cur.right;
        }
    }

    public static void postloop(TreeNode node, List<Integer> visits) {
        Stack stack = new Stack();
        Stack out = new Stack();
        stack.push(node);
        while (!stack.empty()) {
            TreeNode cur = (TreeNode) stack.pop();
            out.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        while (!out.empty()) {
            TreeNode pop = (TreeNode) out.pop();
            visits.add(pop.num);
        }
    }


    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

}
