package com.nettytry;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by adimn on 2019/10/23.
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8081;

        EchoClientHandler clientHandler = new EchoClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(clientHandler);
            }
        });
        ChannelFuture cf = bootstrap.connect().sync();
        cf.channel().closeFuture().sync();
        group.shutdownGracefully().sync();
    }
}
