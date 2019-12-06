package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PriorityQueueTest {
    PriorityQueue pq;

    @Before
    public void init(){
        pq = new PriorityQueue();
    }

    @Test
    public void topKFrequent() {
        int[] num = {1,1,1,2,2,3};
        List<Integer> res = pq.topKFrequent(num, 2);
        res.stream().forEach(System.out::println);

    }
}