package com.ang.firstweb.lettcode;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
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

        List<Integer> integers;
        Map<Integer,List<Integer>> info = new HashMap<>();
        for(int i=0;i<nums.length;i++){

            if(info.containsKey(nums[i])){
                integers = info.get(nums[i]);
                int i1 = integers.get(0) + 1;
                integers.set(0,i1);

                integers.set(2,i);
            }else {
                List<Integer> curinfo = new ArrayList<>(3);
                curinfo.add(1);
                curinfo.add(i);
                curinfo.add(i);

                info.put(nums[i],curinfo);
            }
        }
        int max = -1;
        int min = nums.length+1;
        for(Map.Entry<Integer,List<Integer>> entry:info.entrySet()){
            List<Integer> value = entry.getValue();
            int curmin = value.get(2) - value.get(1) +1;
            if(value.get(0) > max ){
                min = curmin;
                max = value.get(0);
            }else if(value.get(0) == max){
                if( curmin < min) {
                    min = curmin;
                    max = value.get(0);
                }
            }
        }
        return min;
    }


}
