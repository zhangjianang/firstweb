package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/29.
 */
public class ClimbStairs70Test {
    ClimbStairs70 cc;

    @Before
    public void init(){
        cc = new ClimbStairs70();
    }

    @Test
    public void debug(){
        int[][] num2={{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        assertEquals(0,cc.uniquePathsWithObstacles(num2));
    }

    @Test
    public void uniquePathsTest() throws Exception {
        assertEquals(3,cc.uniquePaths(3,2));
        assertEquals(28,cc.uniquePaths(7,3));
    }

    @Test
    public void withObstacle(){
        int[][] num ={{0,0,0},{0,1,0},{0,0,0}};
        System.out.println(num.length);
        System.out.println(num[0].length);
        assertEquals(2,cc.uniquePathsWithObstacles(num));

        int[][] num1={{1,0}};
        assertEquals(0,cc.uniquePathsWithObstacles(num1));

        int[][] num2={{0,1,0,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        assertEquals(0,cc.uniquePathsWithObstacles(num2));
    }

    @Test
    public void longestPalindromeTest(){
        assertEquals("bb",cc.longestPalindrome("cbbd"));
        assertEquals("bab",cc.longestPalindrome("babad"));
        assertEquals("a",cc.longestPalindrome("a"));
        assertEquals("ccc",cc.longestPalindrome("ccc"));
        assertEquals("anana",cc.longestPalindrome("bananas"));
    }

}