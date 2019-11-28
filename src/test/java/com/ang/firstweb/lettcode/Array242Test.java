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
        int [] nums1 = {1, 2, 2,2, 3, 1};

        assertEquals(2,aa.findShortestSubArray(nums));
        assertEquals(3,aa.findShortestSubArray(nums1));

        int [] nums2 = {1,2,2,3,1,4,2};
        assertEquals(6,aa.findShortestSubArray(nums2));

        int [] nums3 = {1,2,2,3,1,4,1};
        assertEquals(nums3.length,aa.findShortestSubArray(nums3));

        int [] nums4 = {2,1,1,2,1,3,3,3,1,3,1,3,2};
        assertEquals(7,aa.findShortestSubArray(nums4));


    }

    @Test
    public void findShortestSubArrayP() throws Exception {

//        [2,1,1,2,1,3,3,3,1,3,1,3,2]

    }


}