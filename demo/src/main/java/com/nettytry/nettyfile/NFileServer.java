package com.nettytry.nettyfile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * Created by adimn on 2019/10/28.
 */
public class NFileServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker= new NioEventLoopGroup();
        bootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(8081)
                .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
//                ch.pipeline().addLast("framer",new FixedLengthFrameDecoder(10));
                ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(18192,
                        Unpooled.wrappedBuffer(new byte[] { '#' })));
//                ch.pipeline().addLast("decoder", new StringDecoder());
                ch.pipeline().addLast(new MyServerHandler());
            }
        });
        ChannelFuture f = bootstrap.bind().sync();
        System.out.println("服务端等待。。。");
        f.channel().closeFuture().sync();
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
    private static class MyServerHandler extends ChannelInboundHandlerAdapter{

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
           ByteBuf buf = (ByteBuf) msg;
            System.out.println("服务端收到");
            System.out.println(buf.toString(Charset.forName("GBK")));
            ctx.write(buf);

        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("服务器端读取完成...");
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
        }
    }
}
