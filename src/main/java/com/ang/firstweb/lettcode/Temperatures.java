package com.ang.firstweb.lettcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by adimn on 2019/11/19.
 */
public class Temperatures {

    public static void convertHiger(int[] in) {
        int[] res = new int[in.length];
        Stack stack = new Stack();
        for (int i = 0; i < in.length; i++) {
            if (stack.size() == 0) {
                stack.push(i);
            } else if (in[(int) stack.peek()] > in[i]) {
                stack.push(i);
            } else {
                int cur = (int) stack.pop();
                res[cur] = i - cur;
            }
        }
        Arrays.stream(res).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int[] ints = {23, 25, 21, 19, 22, 26, 23};
        convertHiger(ints);
    }
}
