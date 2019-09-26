package com.myagent;

/**
 * Created by adimn on 2019/9/26.
 */
public class StringUtils {

    public  String addStringTime(int length) {
        String res = "";
        for (int i = 0; i < length; i++) {
            res += (i % 26 + 'a');
        }
        return res;
    }

    public  String appendStringTime(int length) {
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            res.append(i % 26 + 'a');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        StringUtils utils = new StringUtils();

        int length = 100;

        long begin = System.nanoTime();
        utils.addStringTime(length);
        System.out.println(System.nanoTime() - begin);

        long begin2 = System.nanoTime();
        utils.appendStringTime(length);
        System.out.println(System.nanoTime() - begin2);
    }
}
