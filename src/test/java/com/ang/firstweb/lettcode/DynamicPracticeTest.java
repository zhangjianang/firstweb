package com.ang.firstweb.lettcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/11/29.
 */
public class DynamicPracticeTest {
    DynamicPractice cc;

    @Before
    public void init(){
        cc = new DynamicPractice();
    }

    @Test
    public void debug(){
        assertEquals("bab",cc.longestPalindrome("babad"));
    }

    @Test
    public void isMatchTest(){
        assertEquals(false,cc.isMatch("aa","a"));
        assertEquals(true,cc.isMatch("aa","a*"));
        assertEquals(false,cc.isMatch("mississippi","mis*is*p*."));
        assertEquals(true,cc.isMatch("aab","c*a*b"));
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
        assertEquals("ababababababa",cc.longestPalindrome("ababababababa"));
    }

    @Test
    public void longestValidParenthesesTest() {
        assertEquals(2,cc.longestValidParentheses("(()"));
        assertEquals(4,cc.longestValidParentheses(")()())"));
    }

}