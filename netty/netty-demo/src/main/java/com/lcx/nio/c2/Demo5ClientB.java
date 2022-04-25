package com.lcx.nio.c2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;


public class Demo5ClientB {
    public static void main(String[] args) throws IOException {

        SocketChannel sc = SocketChannel.open(new InetSocketAddress("localhost",8888));
        Selector selector = Selector.open();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);

        new Read(selector).start();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String sendMsg = scanner.nextLine();
            sc.write(ByteBuffer.wrap(sendMsg.getBytes(StandardCharsets.UTF_8)));
        }
    }
    @Slf4j
    public static class Read extends Thread{
        Selector selector;

        public Read(Selector selector){
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
                     if(key.isReadable()){
                         SocketChannel sc = (SocketChannel) key.channel();
                         ByteBuffer buffer = ByteBuffer.allocate(1024);
                         int read = sc.read(buffer);
                         if(read == -1){
                             key.cancel();
                         }
                         buffer.flip();
                         log.info("接收到的数据：{}", Charset.defaultCharset().decode(buffer));
                     }
                }
            }
        }
    }
}
