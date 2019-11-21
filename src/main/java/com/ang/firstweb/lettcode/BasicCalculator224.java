package com.ang.firstweb.lettcode;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by adimn on 2019/11/20.
 */
public class BasicCalculator224 {
    public static int calculate(String s) {
        String[] split = s.replaceAll(" ","").replaceAll("([/\\+\\-\\*\\/\\(\\)])", " $1 ").trim().replaceAll("  ", " ").split(" ");
        ArrayList<String> sb = new ArrayList<>();
        Stack<String> operator = new Stack();
        for (int i = 0; i < split.length; i++) {
            String cur =split[i];
            if (cur.equals("-") ||  cur.equals("+")) {
                while (!operator.empty() && !operator.peek().equals("(") ) {
                    sb.add(operator.pop());
                }
                operator.push(cur);
            } else if (cur.equals("(")) {
                operator.push(cur);
            } else if (cur.equals(")")) {
                while (!operator.empty() && !operator.peek().equals("(")) {
                    sb.add(operator.pop());
                }
                operator.pop();
            } else {
                sb.add(cur);
            }
        }
        while (!operator.empty()) {
            sb.add(operator.pop());
        }
        return evaluate(sb);
    }

    public static int evaluate(List<String> str) {
        Stack<Integer> num = new Stack<>();
        for (int i = 0; i < str.size(); i++) {
            String cur =  str.get(i);
            if (  cur.equals("+")) {
                Integer first = num.pop();
                Integer second = num.pop();
                num.push(first + second);
            } else if (cur.equals("-")) {
                Integer first = num.pop();
                Integer second = num.pop();
                num.push(second - first);
            } else {
                num.push(Integer.parseInt(cur));
            }
        }
        return num.pop();
    }

    public static void main(String[] args) {
        String input = "1+1";
        String input2 = "2 - (1 + 2)";
        String input3 = "(1+(4+5+2)-3)+(6+8)";
        String input4 = "   (  3 ) ";

//        System.out.println(calculate(input3));
        System.out.println(calculate(input4));

//        String[] split = input3.split("([\\+\\-\\*\\/])"," $1 ");
//        String[] split = input3.replaceAll("([/\\+\\-\\*\\/\\(\\)])", " $1 ").trim().replaceAll("  ", " ").split(" ");

//        Arrays.stream(split).forEach(System.out::println);

    }
}
