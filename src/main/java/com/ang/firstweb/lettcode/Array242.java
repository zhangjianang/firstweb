package com.ang.firstweb.lettcode;

import java.util.HashMap;

import java.util.Map;

/**
 * Created by adimn on 2019/11/27.
 */

public class Array242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> nums = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (nums.containsKey(cur)) {
                nums.put(cur, nums.get(cur) + 1);
            } else {
                nums.put(cur, 1);
            }
            char par = t.charAt(i);
            if (nums.containsKey(par)) {
                nums.put(par, nums.get(par) - 1);
            } else {
                nums.put(par, -1);
            }
        }
        for (Map.Entry<Character, Integer> entry : nums.entrySet()) {
            if(entry.getValue()!=0){
                return false;
            }
        }
        return true;
    }

    /**
     *  697 数组的度
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        int max = -1;
        int num = -1;
        for(int i=0;i<nums.length;i++){
            int cur = 1;
            for(int j=i+1;j<nums.length;j++){
                if(nums[j] == nums[i]){
                    cur++;
                }
            }
            if(max<cur){
                num = nums[i];
                max = cur;
            }
        }
        int start = 0;
        boolean first = true;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == num){
                if(first){
                    start = i;
                    first = false;
                }
                max--;
                if(max<1){
                    return i-start;
                }
            }
        }
        return -1;
    }
}
