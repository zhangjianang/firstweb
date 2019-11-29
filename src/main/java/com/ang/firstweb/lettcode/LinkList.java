package com.ang.firstweb.lettcode;

/**
 * Created by adimn on 2019/11/29.
 */
public class LinkList {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for(int i=0;i<k;i++){
            if(temp==null){
                return head;
            }
            temp = temp.next;
        }

//        第k个元素。
        ListNode cur = head;
        ListNode next  = null;
        ListNode pre  = null;
        for(int i=0;i<k;i++){
            if(cur==null){
                break;
            }
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = reverseKGroup(next,k);
        return pre;
    }

    public void showList(ListNode head) {
        System.out.println(head.val);
        while (head.next != null) {
            head = head.next;
            System.out.println(head.val);
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
