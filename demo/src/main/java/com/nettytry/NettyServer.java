package com.nettytry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by adimn on 2019/10/23.
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        int port = 8081;
        NettyEchoServerHandler handler = new NettyEchoServerHandler();
        NioEventLoopGroup bossgroup = new NioEventLoopGroup(1);
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossgroup,loopGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(handler);
                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.bind().sync();
            System.out.println("bind blocked?");
            channelFuture.channel().closeFuture().sync();
            System.out.println("close future blocked?");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully().sync();
            System.out.println("shutdown graceful blocked?");
        }
    }
}
