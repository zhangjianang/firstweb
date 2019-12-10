package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PriorityQueueTest {
    PriorityQueueTry pq;
    KthLargest kl;

    @Before
    public void init(){
        pq = new PriorityQueueTry();
    }

    @Test
    public void topKFrequent() {
        int[] num = {1,1,1,2,2,3};
        pq.topKFrequent(num, 2).stream().forEach(System.out::println);
        System.out.println("----");
        pq.topKFrequentClean(num, 2).stream().forEach(System.out::println);
    }

    @Test
    public void streamKLargest(){
        int[] arr = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, arr);
        assertEquals(4,kthLargest.add(3));
        assertEquals(5,kthLargest.add(5));
        assertEquals(5,kthLargest.add(10));
        assertEquals(8,kthLargest.add(9));
        assertEquals(8,kthLargest.add(4));
    }


    @Test
    public void streamKLargest2(){
        int[] arr = {0};
        KthLargest kthLargest = new KthLargest(2, arr);
        assertEquals(-1,kthLargest.add(-1));
        assertEquals(0,kthLargest.add(1));
        assertEquals(0,kthLargest.add(-2));
        assertEquals(0,kthLargest.add(-4));
        assertEquals(1,kthLargest.add(3));
    }

    @Test
    public void streamKLargest3(){
        int[] arr = {5,-1};
        KthLargest kthLargest = new KthLargest(3, arr);
        assertEquals(-1,kthLargest.add(2));
        assertEquals(1,kthLargest.add(1));
        assertEquals(1,kthLargest.add(-1));
        assertEquals(2,kthLargest.add(3));
        assertEquals(3,kthLargest.add(4));
    }

    @Test
    public void kClosestTest(){
//        int[][] point =  {{1,3},{-2,2}};
//        int[][] exp = {{-2,2}};
//
//        assertEquals(exp,pq.kClosest(point,1));

        int[][] point2 =  {{3,3},{5,-1},{-2,4}};
        int[][] exp1 ={{-2,4},{3,3}};
//        Arrays.stream(pq.kClosest(point2, 2)).forEach(System.out::println);
        assertEquals(exp1,pq.kClosest(point2, 2));
    }
}