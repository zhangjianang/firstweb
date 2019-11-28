package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/28.
 */
public class TemperaturesTest {

    Temperatures tt;

    @Before
    public void init(){
        tt = new Temperatures();
    }

    @Test
    public void maxProfit() throws Exception {
        int[] p = {7,1,5,3,6,4};
        assertEquals(5,tt.maxProfit(p));

        int[] p1 = {7,6,4,3,1};
        assertEquals(0,tt.maxProfit(p1));

    }

    @Test
    public void maxProfitMulti(){
        int[] p = {7,1,5,3,6,4};
        assertEquals(7,tt.maxProfitMulti(p));

        int[] p1 = {1,2,3,4,5};
        assertEquals(4,tt.maxProfitMulti(p1));

        int[] p2 = {7,6,4,3,1};
        assertEquals(0,tt.maxProfitMulti(p2));
    }

}