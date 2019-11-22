package com.ang.firstweb.lettcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adimn on 2019/11/22.
 */
public class ClimbStairs70 {
    private static Map<Integer,Integer> res = new HashMap<>();
    public static int climbStairs(int n) {
        if(n<=1){
            res.put(n,1);
            return 1;
        }
        if(res.containsKey(n)){
            return res.get(n);
        }
        res.put(n,climbStairs(n-2)+climbStairs(n-1));
        return res.get(n);
    }

    public int climbStairs2(int n){
        HashMap<Integer, Integer> data = new HashMap<>();
        return withnum(n,data);
    }

    public int withnum(int n,Map<Integer,Integer> cur){
        if(n<=1){
            cur.put(n,1);
            return 1;
        }
        if(cur.containsKey(n)){
            return cur.get(n);
        }
        cur.put(n,withnum(n-2,cur)+withnum(n-1,cur));
        return cur.get(n);
    }


    public int dyna(int n) {
        int[] dp = new int[n+1];
        dp[0] =1;
        dp[1] =1;
        if(n<2){
            return dp[n];
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }


}
