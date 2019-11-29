package com.ang.firstweb.lettcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2019/11/22.
 */
public class ClimbStairs70 {
    private static Map<Integer, Integer> res = new HashMap<>();

    public static int climbStairs(int n) {
        if (n <= 1) {
            res.put(n, 1);
            return 1;
        }
        if (res.containsKey(n)) {
            return res.get(n);
        }
        res.put(n, climbStairs(n - 2) + climbStairs(n - 1));
        return res.get(n);
    }

    public int climbStairs2(int n) {
        HashMap<Integer, Integer> data = new HashMap<>();
        return withnum(n, data);
    }

    public int withnum(int n, Map<Integer, Integer> cur) {
        if (n <= 1) {
            cur.put(n, 1);
            return 1;
        }
        if (cur.containsKey(n)) {
            return cur.get(n);
        }
        cur.put(n, withnum(n - 2, cur) + withnum(n - 1, cur));
        return cur.get(n);
    }


    public int dyna(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (n < 2) {
            return dp[n];
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public int rob(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] val = new int[nums.length];
        val[0] = nums[0];
        val[1] = Math.max(nums[1], nums[0]);
        int max = Math.max(val[0], val[1]);

        for (int i = 2; i < nums.length; i++) {
            val[i] = Math.max(val[i - 1], val[i - 2] + nums[i]);
            max = Math.max(val[i], max);
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int[] pos = new int[nums.length];
        int[] neg = new int[nums.length];
        pos[0] = nums[0];
        neg[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pos[i] = Math.max(nums[i], Math.max(pos[i - 1] * nums[i], neg[i - 1] * nums[i]));
            neg[i] = Math.min(nums[i], Math.min(pos[i - 1] * nums[i], neg[i - 1] * nums[i]));
            max = Math.max(max, pos[i]);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int[] pp = new int[nums.length];
        pp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (pp[i - 1] < 0) {
                    pp[i] = nums[i];
                } else {
                    pp[i] = pp[i - 1] + nums[i];
                }
            } else {
                if (pp[i - 1] < 0) {
                    pp[i] = nums[i];
                } else {
                    pp[i] = pp[i - 1] + nums[i];
                }
            }
            max = Math.max(pp[i], max);
        }
        return max;
    }


    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {

                if (p.charAt(j) == s.charAt(i)) {
                    dp[i][j] = true;
                }

            }
        }
        return true;
    }

    public int uniquePaths(int m, int n) {
        int[][] num = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 || j == 1) {
                    num[i][j] = 1;
                } else {
                    num[i][j] = num[i - 1][j] + num[i][j - 1];
                }
            }
        }
        return num[m][n];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] num = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    num[i][j] = 0;
                } else if (i == 0) {
                    if (j == 0) {
                        num[i][j] = 1;
                    } else {
                        num[i][j] = num[i][j - 1];
                    }
                } else if (j == 0) {
                    if (i == 0) {
                        num[i][j] = 1;
                    } else {
                        num[i][j] = num[i - 1][j];
                    }
                } else {
                    num[i][j] = num[i - 1][j] + num[i][j - 1];
                }
            }
        }
        return num[m - 1][n - 1];
    }


    public String longestPalindrome(String s) {
        if(s.length()==0){
            return "";
        }
        int max = 0;
        String maxstr = "";
        List<String> temp = new ArrayList<>(s.length());
        temp.add(0, s.charAt(0) + "");
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            String pre = temp.get(i - 1);
            int plen = pre.length();
            if (i - (plen + 1) >= 0 &&s.charAt(i - plen - 1)==cur) {
                temp.add(i, s.charAt(i - plen - 1) + pre + cur);
            } else if (cur == pre.charAt(plen-1)) {
                temp.add(i, pre + "" + cur);
            } else {
                temp.add(i, cur + "");
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            String cur = temp.get(i);
            if (cur.length() > max) {
                max = cur.length();
                maxstr = cur;
            }
        }
        return maxstr;
    }

}
