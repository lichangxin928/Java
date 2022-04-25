package com.lcx.netty.c3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static com.google.common.net.HttpHeaders.CONTENT_LENGTH;

@Slf4j
public class TestHttp {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class)
                .group(boss,worker)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        socketChannel.pipeline().addLast(new HttpServerCodec());
                        socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {
                                log.debug(httpRequest.uri());
                                DefaultFullHttpResponse response =
                                        new DefaultFullHttpResponse(httpRequest.protocolVersion(), HttpResponseStatus.OK);
                                byte[] bytes = "<h1>hello world</h1>".getBytes(StandardCharsets.UTF_8);
                                response.content().writeBytes(bytes);
                                response.headers().setInt(CONTENT_LENGTH,bytes.length);
                                channelHandlerContext.writeAndFlush(response);
                            }
                        });
                    }
                })
                .bind(8888)
                .sync()
                .channel()
                .closeFuture()
                .sync();
    }
}
