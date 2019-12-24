package com.nettytry.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;


public class ChatServerHandler extends ChannelInboundHandlerAdapter {

//    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static Set<Channel> mychannels= new HashSet<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();

        System.out.println(socketAddress.toString() + " 连接成功！");
//        channelGroup.add( ctx.channel());
        mychannels.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {


        System.out.println("服务端收到：" +  msg.toString());
        Channel cur = ctx.channel();
        for(Channel per :mychannels){
            if(per ==cur){
//                System.out.println("转发"+info.toString(CharsetUtil.UTF_8));
                per.writeAndFlush(msg);
            }else{
//                System.out.println("转发"+info.toString(CharsetUtil.UTF_8));
//                ByteBuf rebuf = Unpooled.copiedBuffer(cur.remoteAddress() + " 说：" + copy.toString( CharsetUtil.UTF_8), CharsetUtil.UTF_8);
                per.writeAndFlush(cur.remoteAddress() + " 说：" +msg);
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
