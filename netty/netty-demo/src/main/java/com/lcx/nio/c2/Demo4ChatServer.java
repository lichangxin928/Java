package com.lcx.nio.c2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;


public class Demo4ChatServer {


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        InetSocketAddress address = new InetSocketAddress(8888);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(address);
        // 绑定 accept 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动--->");
        while (true) {
            int select = selector.select();
            if (select == 0) {
                System.out.println("没有任何绑定事件");
                continue;
            }
            // 获取到 selectionKeys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 迭代器
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel accept = ssc.accept();
                    System.out.println("访问者--->" + accept.getRemoteAddress());
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    accept.write(ByteBuffer.wrap("欢迎进入聊天室".getBytes()));
                } else if (selectionKey.isReadable()) {
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    ByteBuffer bf = ByteBuffer.allocate(1024);
                    StringBuilder message = new StringBuilder();
                    while (sc.read(bf) > 0) {
                        bf.flip();
                        message.append(Charset.forName(StandardCharsets.UTF_8.name()).decode(bf));
                    }
                    if (message.toString().equals("")) {
                        continue;
                    }
                    //广播群发
                    Set<SelectionKey> keys = selector.keys();
                    for (SelectionKey key : keys) {
                        SelectableChannel tr = key.channel();
                        // 不自己给自己发送消息
                        if (tr == sc){
                            continue;
                        }
                        if (tr instanceof SocketChannel ){
                            SocketChannel socketChannel = (SocketChannel) tr;
                            System.out.println("发送信息给-->"+ socketChannel.getRemoteAddress()+"-->message: "+ message);
                            socketChannel.write(ByteBuffer.wrap(message.toString().getBytes()));
                        }
                    }
                }
            }
        }
    }
}
