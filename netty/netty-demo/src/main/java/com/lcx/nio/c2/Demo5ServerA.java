package com.lcx.nio.c2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;


@Slf4j
public class Demo5ServerA {

    public static SocketChannel socketChannel = null;

    public static void main(String[] args) throws Exception {

        // 初始化server
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost",8888));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        // 存在问题，主线程没有干任何事情
        new Connect(selector).start();

    }

    public static class Connect extends Thread{
        Selector selector;
        public Connect(Selector selector){
            this.selector = selector;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true){
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    // 处理 accept 事件
                    if(key.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        socketChannel = channel.accept();
                        log.debug("{} 连接到了服务器",socketChannel.getRemoteAddress());
                        // 绑定 Read 事件
                        new Send(socketChannel).start();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    } else if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        if(channel.finishConnect()) {
                            key.channel();
                            break;
                        }
                        int read = channel.read(buffer);
                        // 关闭连接的
                        if(read == -1){
                            key.cancel();
                        }
                        buffer.flip();
                        log.debug("接收到的消息：{}", Charset.defaultCharset().decode(buffer));
                    }
                }
            }
        }
    }

    public static class Send extends Thread{
        SocketChannel socketChannel;

        public Send(SocketChannel socketChannel){
            this.socketChannel = socketChannel;
        }

        @SneakyThrows
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            log.info("服务器准备好向客户端发送消息.....");
            while (true) {
                if (socketChannel != null) {
                    String input = scanner.nextLine();
                    if(socketChannel.finishConnect()) break;
                    socketChannel.write(ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8)));
                }
            }
            log.info("会话结束。。。。");
        }
    }
}
