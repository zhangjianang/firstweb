package com.ang.firstweb.lettcode;

import java.util.*;

public class PriorityQueue {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequent = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (frequent.containsKey(cur)) {
                frequent.put(cur, frequent.get(cur) + 1);
            } else {
                frequent.put(cur, 1);
            }
        }
        Object[] keys = frequent.keySet().toArray();


        for (int i = keys.length / 2; i >= 0; i--) {
            rebalance(keys, i, keys.length, frequent);
        }

        List<Integer> res = new ArrayList<>();
        int len = keys.length - 1;
        for (int j = 0; j < k; j++) {
            res.add(Integer.parseInt(keys[0].toString()));
            numswitch(keys, 0, len - j);
            rebalance(keys, 0, len - j, frequent);
        }
        return res;
    }

    public void rebalance(Object[] num, int start, int end, Map<Integer, Integer> frequent) {
        int l = 2 * start + 1;
        int r = 2 * start + 2;
        if (l < end && r < end) {
            if (frequent.get(num[l]) < frequent.get(num[r])) {
                if (frequent.get(num[start]) < frequent.get(num[r])) {
                    numswitch(num, start, r);
                    rebalance(num, r, end, frequent);
                }
            } else {
                if (frequent.get(num[start]) < frequent.get(num[l])) {
                    numswitch(num, start, l);
                    rebalance(num, l, end, frequent);
                }
            }
        } else if (l < end) {
            if (frequent.get(num[start]) < frequent.get(num[l])) {
                numswitch(num, start, l);
            }
        }
    }

    public void numswitch(Object[] num, int first, int second) {
        Object temp = num[first];
        num[first] = num[second];
        num[second] = temp;
    }

    public static void main(String[] args) {
//        int[] num = {3,6,1,7,4,9,3};
        Integer[] num = new Integer[]{3, 6, 1, 7, 4, 9, 3};
        PriorityQueue pq = new PriorityQueue();
        for (int i = num.length / 2; i >= 0; i--) {
            pq.rebalance(num, i, num.length, null);
        }

        int len = num.length - 1;
        for (int j = 0; j < 3; j++) {
            pq.numswitch(num, 0, len - j);
            pq.rebalance(num, 0, len - j, null);
        }
        Arrays.stream(num).forEach(System.out::println);
    }
}

class KthLargest {
    private java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue();
    public KthLargest(int k, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if (pq.size()>k) {
                pq.poll();
            }
        }
    }
    public int add(int val) {
        if(val>pq.peek()) {
            pq.add(val);
            pq.poll();
        }
        return  pq.peek();
    }

    public static void main(String[] args) {
        int[] arr = {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, arr);

        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10)); // returns 5
        System.out.println(kthLargest.add(9));
        // returns 8
        System.out.println(kthLargest.add(4));
        // returns 8

//["KthLargest","add","add","add","add","add"]
//[[1,[]],[-3],[-2],[-4],[0],[4]]
    }
}