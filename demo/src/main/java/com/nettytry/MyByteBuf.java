package com.nettytry;

import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by adimn on 2019/10/28.
 */
public class MyByteBuf {
    public static void main(String[] args) {
//        byteBufSetGet();
//        bufWriteRead();
//        slice();
        composeBuf();
    }
    public static void byteBufSetGet(){
        io.netty.buffer.ByteBuf cop = Unpooled.copiedBuffer("昂 are the champion", Charset.forName("UTF-8"));

        System.out.println("read index :"+cop.readerIndex());
        System.out.println("write index :"+cop.writerIndex());

        io.netty.buffer.ByteBuf copa = cop.setByte(0, 'a');
        System.out.println((char)cop.getByte(0));
        System.out.println((char)copa.getByte(0));
    }
    public static void bufWriteRead(){
        Charset utf8 = Charset.forName("UTF-8");
        io.netty.buffer.ByteBuf buf = Unpooled.copiedBuffer("我们 the champion", utf8);
        buf.writeBytes("今天开始 ".getBytes());
        System.out.println(buf.toString(utf8));
        System.out.println("readerIndex:"+buf.readerIndex()+",writerIndex:"+buf.writerIndex());
        buf.readByte();
        System.out.println("readerIndex:"+buf.readerIndex()+",writerIndex:"+buf.writerIndex());
    }

    public static void slice(){
        Charset utf8 = Charset.forName("UTF-8");
        io.netty.buffer.ByteBuf buf = Unpooled.copiedBuffer("we are the champion今天", utf8);
        io.netty.buffer.ByteBuf slice = buf.slice(0, 14);
        io.netty.buffer.ByteBuf copy = buf.copy(0, 10);
        System.out.println("copy is :"+copy.toString(utf8));
        System.out.println(slice.toString(utf8));
        System.out.println(buf.toString(utf8));
    }

    public static void composeBuf(){
        CompositeByteBuf com = Unpooled.compositeBuffer();
        Charset utf8 = Charset.forName("UTF-8");
        io.netty.buffer.ByteBuf headerBuf = Unpooled.copiedBuffer("Header", utf8);
        io.netty.buffer.ByteBuf bodyBuf = Unpooled.copiedBuffer("This is body", utf8);
        com.addComponent(headerBuf);
        com.addComponent(bodyBuf);
        for(io.netty.buffer.ByteBuf per:com){
            System.out.println(per.toString(utf8));
        }
    }
}
