package com.nettytry.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * Created by adimn on 2019/10/29.
 */
public class RpcRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        RpcRequest request = new RpcRequest();
        request.setClassname("com.nettytry.rpc.CalculateImpl");
        request.setMethodname("add");
        request.setParam1(1.0);
        request.setParam2(2.0);
        ctx.writeAndFlush(request);
        System.out.println("客户端写入。。。"+request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcResponse msg1 = (RpcResponse) msg;

        System.out.println("客户端收到:"+msg1.getReponse());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端写完");
    }
}
