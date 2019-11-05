package com.ang.firstweb.pattern;

/**
 * Created by adimn on 2019/11/5.
 */
public class EasyTemplate extends TempLate {
    @Override
    void methodA() {
        System.out.println("真好使");
    }

    public static void main(String[] args) {
        TempLate easy = new EasyTemplate();
        easy.dosth();
    }
}
