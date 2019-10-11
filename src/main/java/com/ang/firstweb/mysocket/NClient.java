package com.ang.firstweb.mysocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by adimn on 2019/10/11.
 */
public class NClient {
    public static void main(String[] args) {
        try {
            SocketChannel sc = SocketChannel.open();

            Selector selector = Selector.open();
            sc.open(new InetSocketAddress("127.0.0.1",8003));
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_WRITE);

            while(selector.select()>0){
                Set<SelectionKey> keys = selector.keys();
                for(SelectionKey key:keys){
                    if(key.isWritable()){
                        SocketChannel wsc = (SocketChannel)key.channel();
                        String word = "hello";
                        ByteBuffer allocate = ByteBuffer.allocate(word.length());
                        allocate.put(word.getBytes());
                        allocate.flip();
                        wsc.write(allocate);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
