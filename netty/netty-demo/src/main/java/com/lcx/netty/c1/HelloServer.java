package com.lcx.netty.c1;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

public class HelloServer {

    public static void main(String[] args) {
        // 1. 服务器端的启动器，负责组装 netty 组件，启动服务器
        new ServerBootstrap()
                // 2. group 组
                .group(new NioEventLoopGroup())
                // 3. 选择服务器的 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                // 4. boss 负责处理连接，worker 负责处理读写，决定了worker(child) 要执行什么操作(handler)
                .childHandler(
                        // 5. channel 代表和客户端进行数据读写的通道 Initializer 初始化，负责添加别的handler
                        new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 6. 添加具体handler
                        channel.pipeline().addLast(new StringDecoder()); // 将ByteBuf 转换为字符串
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义handler
                            @Override // 读事件
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 绑定监听端口
                .bind(new InetSocketAddress("localhost",8888));
    }
}
