package com.nettytry.nettyfile;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


import java.io.RandomAccessFile;

import java.net.InetSocketAddress;

import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by adimn on 2019/10/28.
 */
public class NFileClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup loop = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        String host = "127.0.0.1";
        int port = 8081;
        bootstrap.group(loop)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyClienthandler());
                    }
                });
        ChannelFuture f = bootstrap.connect().sync();
        f.channel().closeFuture().sync();
        loop.shutdownGracefully().sync();
    }

    private static class MyClienthandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

            System.out.println("客户端发送开始");
            RandomAccessFile file = new RandomAccessFile("D:\\data\\parseDetail.txt", "rw");
            FileChannel fc = file.getChannel();
            MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
//            ctx.writeAndFlush(map);
            ByteBuf byteBuf = Unpooled.copiedBuffer(map);
            CompositeByteBuf com = Unpooled.compositeBuffer();
            ByteBuf buf =  ctx.alloc().directBuffer();
             buf.writeBytes("#".getBytes());
            com.addComponents(true,byteBuf,Unpooled.wrappedBuffer(new byte[] { '#' }));
            ctx.writeAndFlush(com);
            System.out.println("客户端写完");
            ctx.close();

        }
    }

}
