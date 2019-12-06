package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/22.
 */
public class ClimbStairsTest {
    DynamicPractice cc;

    @Before
    public void init() {
        cc = new DynamicPractice();
    }

    @Test
    public void climbStairs() throws Exception {
        assertEquals(2, DynamicPractice.climbStairs(2));
        assertEquals(2, cc.climbStairs2(2));
    }

    @Test
    public void climbStairs1() throws Exception {
        assertEquals(21, DynamicPractice.climbStairs(7));
        assertEquals(21, cc.climbStairs2(7));
    }

    @Test
    public void climbStairs2() throws Exception {
        assertEquals(13, DynamicPractice.climbStairs(6));
        assertEquals(13, cc.climbStairs2(6));
    }

    @Test
    public void climbStairs3() throws Exception {
        assertEquals(39088169, cc.climbStairs2(37));
    }

    @Test
    public void climbStairs4() throws Exception {
//        assertEquals(2, cc.dyna(2));
        assertEquals(39088169, cc.dyna(37));
    }


    @Test
    public void robtest() {
        int[] nums = {1, 2, 3, 1};
        assertEquals(4, cc.rob(nums));
    }

    @Test
    public void maxSubArray() {
        int[] nums = {1, 2, 3, 1};
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(7, cc.maxSubArray(nums));
        assertEquals(6, cc.maxSubArray(nums1));
    }

    @Test
    public void isMatch() {
        String str = "aa";
        String p = "a";
        assertEquals(true, cc.isMatch(str, p));
        String str1 = "aa";
        String p1 = "a*";
        assertEquals(true, cc.isMatch(str1, p1));
        String str2 = "ab";
        String p2 = ".*";
        assertEquals(true, cc.isMatch(str2, p2));
        String str3 = "aab";
        String p3 = "c*a*b";
        assertEquals(true, cc.isMatch(str3, p3));
        String str4 = "mississippi";
        String p4 = "mis*is*p*.";
        assertEquals(true, cc.isMatch(str4, p4));
    }
}