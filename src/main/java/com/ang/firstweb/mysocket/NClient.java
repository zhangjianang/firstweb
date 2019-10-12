package com.ang.firstweb.mysocket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by adimn on 2019/10/11.
 */
public class NClient {
    public static Charset charset = Charset.forName("utf-8");

    public static void main(String[] args) {
        try (SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8003));
             Selector selector = Selector.open();
             Scanner scanner = new Scanner(System.in);) {
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);

            new Thread(new NClientReceive(selector)).start();

            System.out.println("say sth:");
            while (scanner.hasNext()) {
                String content = scanner.nextLine();
                sc.write(charset.encode(content));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NClientReceive implements Runnable {
    Selector selector;

    public NClientReceive(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (selector.select()>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for(SelectionKey key:selectionKeys){
                    selector.selectedKeys().remove(key);

                    if(key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        StringBuilder content = new StringBuilder();

                        while(sc.read(buffer)>0){
                            buffer.flip();
                            content.append(NClient.charset.decode(buffer));
                        }
                        System.out.println("client收到："+content.toString());
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
