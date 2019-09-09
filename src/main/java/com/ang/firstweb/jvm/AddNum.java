package com.ang.firstweb.jvm;

/**
 * Created by adimn on 2019/9/9.
 */
public class AddNum {
    public int add(){
        int a = 10;
        int b = 14;
        int c = (a+10)*b;
        return c;
    }
    public static void main(String[] args) {
        AddNum addNum = new AddNum();
        System.out.println(addNum.add());
    }
}
