package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/29.
 */
public class LinkListTest {
    LinkList ll;

    @Before
    public void init(){
        ll = new LinkList();

    }

    @Test
    public void reverseKGroup() throws Exception {
        ListNode first =new ListNode(1);
        ListNode head = first;
        for(int i=2;i<6;i++){
            ListNode next = new ListNode(i);
            first.next = next;
            first = first.next;
        }
        ll.showList(head);
        ListNode nh = ll.reverseKGroup(head, 2);
        System.out.println("----");
        ll.showList(nh);
    }


}