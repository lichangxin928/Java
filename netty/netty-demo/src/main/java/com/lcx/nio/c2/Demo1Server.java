package com.lcx.nio.c2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Demo1Server {
    public static void main(String[] args) throws Exception{

        final ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8888));
        // 3. 连接集合
        // List<SocketChannel> channels = new ArrayList<>();
        while (true){
            log.debug("connecting....");
            // 4. 建立与客户端俩你今儿，SocketChannel 用来与客户端之间通信
            SocketChannel sc = ssc.accept();    // 阻塞方法
            log.debug("connected...{}",sc);
            //channels.add(sc);

            final Runnable runnable = new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    while (true) {
                        if(!sc.isConnected()){
                            log.debug("连接断开:{}",sc);
                        }
                        //for (SocketChannel channel : channels) {
                        // 5. 接收客户端发送的数据
                        log.debug("before read..");
                        sc.read(buffer);   // 阻塞方法
                        buffer.flip();
                        readBuffer(buffer);
                        buffer.clear();
                        log.debug("after read...{}", sc);
                        //}
                    }
                }
            };
            threadPool.submit(runnable);

        }

    }
    public static void readBuffer(ByteBuffer buffer){
        while (buffer.hasRemaining()){
            System.out.print((char) buffer.get());
        }
        System.out.println("");
    }
}
