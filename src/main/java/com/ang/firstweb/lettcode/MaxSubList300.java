package com.ang.firstweb.lettcode;

import java.util.Arrays;

/**
 * Created by adimn on 2019/11/21.
 */
public class MaxSubList300 {
    // 定义一个静态变量 max，用来保存最终的最长的上升子序列的长度
    static int max;

    public static int lengthOfLIS(int[] nums) {
        max = 1;
        f(nums, nums.length);
        return max;
    }

    public static int f(int[] nums, int n) {
        if (n <= 1) {
            return n;
        }

        int result = 0, maxEndingHere = 1;

        // 从头遍历数组，递归求出以每个点为结尾的子数组中最长上升序列的长度
        for (int i = 1; i < n; i++) {
            result = f(nums, i);

            if (nums[i - 1] < nums[n - 1] && result + 1 > maxEndingHere) {
                maxEndingHere = result + 1;
            }
        }

        // 判断一下，如果那个数比目前最后一个数要小，那么就能构成一个新的上升子序列
        if (max < maxEndingHere) {
            max = maxEndingHere;
        }

        // 返回以当前数结尾的上升子序列的最长长度
        return maxEndingHere;
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxmid = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxmid = Math.max(maxmid, dp[j]);
                }
                dp[i] = maxmid + 1;
                max = Math.max(max, dp[i]);
            }
        }
        Arrays.stream(dp).forEach(System.out::println);
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums1 = {0};
        int[] nums2 = {4, 10, 4, 3, 8, 9};

        System.out.println("res-" + lengthOfLIS2(nums2));

        recLen(nums2, nums2.length);
    }


    public static int lengthOfLIS3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int midmax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    midmax = Math.max(midmax, dp[j]);
                }
            }
            dp[i] = midmax + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public static int recLen(int[] nums, int n) {
        if (n <= 1) {
            return n;
        }
        int result, maxmid = 0;
        for (int i = 1; i < n; i++) {

            result = recLen(nums, i);
            if (nums[i-1] < nums[n-1] && result + 1 > maxmid) {
                maxmid = result + 1;
            }

        }
        if (maxmid > max) {
            max = maxmid;
        }
        System.out.println(n + "--" + maxmid);
        return maxmid;
    }
}
