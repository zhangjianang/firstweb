package com.nettytry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

/**
 * Created by adimn on 2019/10/25.
 */
public class AngServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.channel(NioServerSocketChannel.class);
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss,work).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new HttpRequestDecoder())
                        .addLast(new HttpResponseEncoder())
                        .addLast("",new AngHandler());
            }
        });
        try {
            ChannelFuture future = bootstrap.bind(8081).sync();
            System.out.println("等待连接 8081");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static class AngHandler extends SimpleChannelInboundHandler{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html;charset=utf8");
            response.content().writeBytes("昂 is a good man".getBytes());
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
