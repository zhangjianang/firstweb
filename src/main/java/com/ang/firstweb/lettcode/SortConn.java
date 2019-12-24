package com.ang.firstweb.lettcode;

import java.util.Random;

public class SortConn {

    public ListNode insertionSortList(ListNode head) {
        ListNode empty = new ListNode(-1);
        ListNode cur = head;

        while(cur != null){
            ListNode pre = empty.next;
            while(pre!=null && pre != cur && pre.val < cur.val){
                pre = pre.next;
            }
            ListNode temp = new ListNode( cur.val);

            if(pre!=null ){
                ListNode pn =  pre ;
                pre = temp;
                if(pn!=null ) {
                    pre.next = pn;
                }
            }else{
                pre = temp;
                empty.next= pre;
            }
            cur = cur.next;
        }
        return empty;
    }

//    3 -> 6-> 2 -> 1 -> 9
//    3
//    3 -> 6


    public static void main(String[] args) {
        ListNode pre = new ListNode(-1);
        ListNode f = pre;
        for(int i=0;i<9;i++){
            pre.next = new ListNode(new Random().nextInt(30));
            pre = pre.next;
        }
        SortConn ss = new SortConn();
        ListNode re = ss.insertionSortList(f.next);
        while(re!=null){
            System.out.println(re.val);
            re = re.next;
        }
    }
}





