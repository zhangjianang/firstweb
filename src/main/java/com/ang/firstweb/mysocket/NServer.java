package com.ang.firstweb.mysocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Set;

/**
 * Created by adimn on 2019/10/11.
 */
public class NServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            Selector selector = Selector.open();
            InetSocketAddress isa = new InetSocketAddress(8003);
            server.bind(isa);

            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                for (SelectionKey key : selector.selectedKeys()) {
                    selector.selectedKeys().remove(key);

                    if (key.isAcceptable()) {

                        SocketChannel sc = server.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        key.interestOps(SelectionKey.OP_ACCEPT);
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        StringBuilder content = new StringBuilder();
                        Charset charset = Charset.forName("UTF-8");
                        CharsetDecoder cd = charset.newDecoder();
                        while (channel.read(allocate) > 0) {
                            allocate.flip();
                            content.append(cd.decode(allocate));
                        }
                        System.out.println("server收到：" + content.toString());

                        key.interestOps(SelectionKey.OP_READ);

                        if (!(content.length() > 0)) continue;
                        for (SelectionKey wkey : selector.keys()) {
                            SelectableChannel targetChannel = wkey.channel();
                            if (!(targetChannel instanceof SocketChannel)) {
                                continue;
                            }
                            SocketChannel wsc = (SocketChannel) targetChannel;
                            wsc.write(charset.encode(content.toString()));
                        }

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
