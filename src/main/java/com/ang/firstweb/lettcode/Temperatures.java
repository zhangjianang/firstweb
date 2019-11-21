package com.ang.firstweb.lettcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by adimn on 2019/11/19.
 */
public class Temperatures {

    public static void convertHiger(int[] T) {
        int[] res = new int[T.length];
        Stack stack = new Stack();
        for (int i = 0; i < T.length; i++) {
            if (stack.size() == 0) {
                stack.push(i);
            } else if (T[(int) stack.peek()] > T[i]) {
                stack.push(i);
            } else {
                while (stack.size() > 0 && T[(int) stack.peek()] < T[i]) {
                    int cur = (int) stack.pop();
                    res[cur] = i - cur;
                }
                stack.push(i);
            }
        }
        Arrays.stream(res).forEach(System.out::println);
    }

    public static void main(String[] args) {
        int[] ints = {23, 24,25, 21, 19, 22, 26, 23};
        convertHiger(ints);
    }
}
