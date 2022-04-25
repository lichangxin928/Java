package com.lcx.nio.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

@Slf4j
public class Demo2ServerNio {
    public static void main(String[] args) throws Exception {

        // 1. 创建selector
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8888));
        ssc.configureBlocking(false);

        // 2. 建立 selector 和channel 的联系(注册)
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和哪个 channel 的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);


        while (true) {
            // 3. select 方法,没有事件发生，线程阻塞，有事件发生继续向下执行
            selector.select();
            // 4. 处理事件，selectKeys 里面包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                log.debug("key:{}",key);
                // 5. 区分事件类型
                if(key.isAcceptable()){
                    // 如果是 accept
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}",sc);
                }else if(key.isReadable()) {
                    try{
                        // 如果是 read
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        buffer.flip();
                        if(read == -1) key.cancel();
                        System.out.println(Charset.defaultCharset().decode(buffer));
                    }catch (IOException e){
                        e.printStackTrace();
                        key.cancel();
                    }
                }
            }
        }
    }
}
