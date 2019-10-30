package com.nettytry.rpc;

import com.nettytry.rpcv1.*;
import com.nettytry.rpcv1.RpcClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;


/**
 * Created by adimn on 2019/10/29.
 */
public class RpcRequestHandler extends ChannelInboundHandlerAdapter {
    private com.nettytry.rpcv1.RpcClient client;

    public RpcRequestHandler(RpcClient client){
        this.client = client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

//        RpcRequest request = new RpcRequest();
//        request.setClassname("com.nettytry.rpc.CalculateImpl");
//        request.setMethodname("add");
//        request.setParam1(1.0);
//        request.setParam2(2.0);
//        ctx.writeAndFlush(request);
//        System.out.println("客户端写入。。。"+request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcResponse msg1 = (RpcResponse) msg;
        BlockingQueue que = client.getQueById(msg1.getId());
        que.put(msg1);
//        System.out.println("客户端收到:"+msg1.getReponse());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端写完");
    }
}
