package com.nettytry.rpcv1;

import com.nettytry.rpc.RpcRequest;
import com.nettytry.rpc.RpcRequestHandler;
import com.nettytry.rpc.RpcResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by adimn on 2019/10/30.
 */
public class RpcClient {

    private Channel channel;

    private  ConcurrentHashMap<String,BlockingQueue> tasks = new ConcurrentHashMap<>();

    public RpcClient(){
    }

    public void init(String host,int port)  {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        RpcRequestHandler rpcRequestHandler = new RpcRequestHandler(this);
        bootstrap.group(group)
                .remoteAddress(new InetSocketAddress(host,port))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ObjectDecoder(
                        1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())
                ));
                ch.pipeline().addLast(new ObjectEncoder());
                ch.pipeline().addLast(rpcRequestHandler);
            }
        });
        try {
             channel = bootstrap.connect().sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public RpcResponse sendRequest(RpcRequest request)  {

        LinkedBlockingDeque<RpcResponse> que = new LinkedBlockingDeque<>();
        tasks.put(request.getId(),que);
        channel.writeAndFlush(request);
        RpcResponse take = null;
        try {
             take = que.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return take;
    }

    public  BlockingQueue getQueById(String id){
        return tasks.get(id);
    }
}
