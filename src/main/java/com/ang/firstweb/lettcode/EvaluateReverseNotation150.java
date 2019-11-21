package com.ang.firstweb.lettcode;

import java.util.Stack;

/**
 * Created by adimn on 2019/11/21.
 */
public class EvaluateReverseNotation150 {

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack();
        for (int i = 0; i < tokens.length; i++) {
            String cur = tokens[i];
            if ("+".equals(cur)) {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(first + second + "");
            } else if ("-".equals(cur)) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());

                stack.push(first - second + "");
            } else if ("*".equals(cur)) {
                int first = Integer.parseInt(stack.pop());
                int second = Integer.parseInt(stack.pop());
                stack.push(first * second + "");
            } else if ("/".equals(cur)) {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                stack.push(first / second + "");
            } else {
                stack.push(cur);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        String[] input = {"2", "1", "+", "3", "*"};
        String[] input2 = {"4", "13", "5", "/", "+"};
        String[] input3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(input3));
    }
}
