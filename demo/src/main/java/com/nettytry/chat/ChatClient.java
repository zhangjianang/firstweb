package com.nettytry.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;

import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(boss)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1",9001))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        //向pipeline加入解码器
//                        pipeline.addLast("decoder", new StringDecoder());
//                        //向pipeline加入编码器
//                        pipeline.addLast("encoder", new StringEncoder());
                        pipeline.addLast(new ChatClientHandler());
                    }
                });
        try {
            ChannelFuture future =  bootstrap.connect().sync();
            //得到 channel
            Channel channel = future.channel();
            System.out.println("========" + channel.localAddress() + "========");
            //客户端需要输入信息， 创建一个扫描器
            /**/
            Scanner scanner = new Scanner(System.in);
            System.out.println("please input sth !");
            while (scanner.hasNextLine()) {
                ByteBuf byteBuf = Unpooled.copiedBuffer(scanner.next(), CharsetUtil.UTF_8);
                //通过 channel 发送到服务器端
                channel.writeAndFlush(byteBuf);
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();

        }
    }
}
