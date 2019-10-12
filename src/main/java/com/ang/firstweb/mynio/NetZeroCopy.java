package com.ang.firstweb.mynio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * Created by adimn on 2019/10/12.
 */
public class NetZeroCopy {
    public static void main(String[] args) {
        try (
                ServerSocketChannel ssc = ServerSocketChannel.open();
                Selector selector = Selector.open();
        ) {
            InetSocketAddress isa = new InetSocketAddress(8000);
            ssc.bind(isa);
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for(SelectionKey key:selectionKeys){
                    selector.selectedKeys().remove(key);
                    if(key.isAcceptable()){
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.register(selector,SelectionKey.OP_READ);
                        key.interestOps(SelectionKey.OP_ACCEPT);
                    }
                    if(key.isReadable()){

                        SelectableChannel tsc = key.channel();
                        if(!(tsc instanceof SocketChannel)) continue;
                        SocketChannel rsc = (SocketChannel) tsc;

                        RandomAccessFile raf = new RandomAccessFile("D:\\receive.txt", "rw");
                        FileChannel fc = raf.getChannel();

                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        while (rsc.read(buffer)>0){
                            fc.write(buffer);
                        }
                        System.out.println("接受完成");
                        key.interestOps(SelectionKey.OP_READ);
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
