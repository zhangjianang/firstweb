package com.ang.firstweb.mynio;

import java.nio.CharBuffer;

/**
 * Created by adimn on 2019/10/9.
 */
public class BufferTry {
    public static void main(String[] args) {
        CharBuffer buff = CharBuffer.allocate(10);
        System.out.println("capacity:"+buff.capacity());
        System.out.println("position:"+buff.position());
        System.out.println("limit:"+buff.limit());

        buff.put('a');
        buff.put('b');
        buff.put('c');

        System.out.println("执行flip()之后"+ buff.flip());
        System.out.println("capacity:"+buff.capacity());
        System.out.println("position:"+buff.position());
        System.out.println("limit:"+buff.limit());
        System.out.println();

        System.out.println("get:"+buff.get());
        System.out.println("执行get()之后");
        System.out.println("position:"+buff.position());
        System.out.println("limit:"+buff.limit());
        System.out.println();

        System.out.println("compact:"+buff.compact());
        System.out.println("执行compact()之后");
        System.out.println("position:"+buff.position());
        System.out.println("limit:"+buff.limit());
        System.out.println();


        System.out.println("执行flip()之后"+ buff.flip());
        System.out.println("capacity:"+buff.capacity());
        System.out.println("position:"+buff.position());
        System.out.println("limit:"+buff.limit());
        System.out.println();

        System.out.println("clear:"+buff.clear());
        System.out.println("执行clear()之后");
        System.out.println("position:"+buff.position());
        System.out.println("limit:"+buff.limit());
        System.out.println();
    }
}
