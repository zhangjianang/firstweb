package com.ang.firstweb.lettcode;

/**
 * Created by adimn on 2019/11/20.
 */
public class LargestRectangle84 {

    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            int cur = len(heights, i) * heights[i];
            if (cur > res) {
                res = cur;
            }
        }

        return res;
    }

    public static int len(int[] heights, int cur) {

        int num = 0;
        for (int j = cur+1 ; j < heights.length; j++) {
            if (heights[j] < heights[cur]) {
                break;
            }
            num++;
        }

        for (int i = cur; i >= 0; i--) {
            if (heights[i] < heights[cur]) {
                break;
            }
            num++;
        }
        return num ;
    }

    public static void main(String[] args) {
        int[] data = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(data));

        int[] data1 = {2, 1, 2};
        System.out.println(largestRectangleArea(data1));

//        System.out.println(len(data, 2));

    }
}
