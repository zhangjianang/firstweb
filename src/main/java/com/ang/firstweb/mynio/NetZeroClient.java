package com.ang.firstweb.mynio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by adimn on 2019/10/12.
 */
public class NetZeroClient {
    public static void main(String[] args) {
        try (
                SocketChannel sc = SocketChannel.open();
                FileChannel fc = new FileInputStream("C:\\Users\\adimn\\Desktop\\bx.txt").getChannel();
                ){
            sc.connect(new InetSocketAddress("127.0.0.1",8000));
            fc.transferTo(0,fc.size(),sc);
            System.out.println("传输完成！");
        } catch (IOException e) {


        }
    }
}
