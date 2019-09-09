package com.ang.firstweb.jvm;

/**
 * Created by adimn on 2019/9/9.
 */
public class MyStackOverFlow {
    private static int num = 0;
    private void add(){
        num++;
        add();
    }

//    -Xss128k  控制stack大小
    public static void main(String[] args) {
        MyStackOverFlow myStackOverFlow = new MyStackOverFlow();
        try {
            myStackOverFlow.add();
        }catch (Exception e ){

        }finally {
            System.out.println(num);
        }
    }
}
