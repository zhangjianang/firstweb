package com.nettytry.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup loop = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, loop)
                .channel(NioServerSocketChannel.class)
                .localAddress(9001)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //向pipeline加入解码器
                        pipeline.addLast("decoder", new StringDecoder());
                        //向pipeline加入编码器
                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast(new ChatServerHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println("等待bind！");
            channelFuture.channel().closeFuture().sync();
            System.out.println("等待关闭！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            loop.shutdownGracefully();
        }

    }
}
