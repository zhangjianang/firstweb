package com.nettytry.rpc;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.lang.reflect.Method;

/**
 * Created by adimn on 2019/10/29.
 */
public class RpcServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        Class<?> clazz = Class.forName(request.getClassname());
        Object newInstance = clazz.newInstance();
        Method method = clazz.getMethod(request.getMethodname(),double.class,double.class);
        Object res = method.invoke(newInstance, request.getParam1(), request.getParam2());

        System.out.println("结果是："+res);

        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setClassname(request.getClassname());
        rpcResponse.setReponse(res);
        rpcResponse.setMethodname(request.getMethodname());
        ctx.writeAndFlush(rpcResponse);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }
}
