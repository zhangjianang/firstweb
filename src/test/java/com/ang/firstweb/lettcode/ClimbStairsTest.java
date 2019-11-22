package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/22.
 */
public class ClimbStairsTest {
    ClimbStairs70 cc ;
    @Before
    public void init(){
        cc = new ClimbStairs70();
    }
    @Test
    public void climbStairs() throws Exception {
        assertEquals(2,ClimbStairs70.climbStairs(2));
        assertEquals(2, cc.climbStairs2(2));
    }

    @Test
    public void climbStairs1() throws Exception {
        assertEquals(21,ClimbStairs70.climbStairs(7));
        assertEquals(21, cc.climbStairs2(7));
    }

    @Test
    public void climbStairs2() throws Exception {
        assertEquals(13,ClimbStairs70.climbStairs(6));
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

}