package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/27.
 */
public class Array242Test {
    Array242 aa;

    @Before
    public void init(){
        aa= new Array242();
    }

    @Test
    public void isAnagram() throws Exception {
        String s = "anagram", t = "nagaram";
        assertEquals(true,aa.isAnagram(s,t));
    }
    @Test
    public void findShortestSubArray() throws Exception {
        int [] nums = {1, 2, 2, 3, 1};
        assertEquals(3,aa.findShortestSubArray(nums));
    }


}