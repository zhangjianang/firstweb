package com.ang.firstweb.grammer;

import java.io.IOException;

/**
 * Created by ang on 2019/9/22.
 */
public class TryCatchExample {
    public static void main(String[] args) {
        System.out.println(get1());
        System.out.println(get2());
    }

    public static int get1() {
        int i = 10, res = 0;
        try {
            res++;
            return res;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return i;
        } finally {
            res += 10;
        }
    }

    public static int get2() {
        int i = 10, res = 0;
        try {
            res++;
            int j = i / (res - 1);
            return res;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return i;
        } finally {
            res += 10;
            i=1;
        }
    }
}
