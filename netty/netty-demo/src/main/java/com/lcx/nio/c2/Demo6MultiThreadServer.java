package com.lcx.nio.c2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

@Slf4j
public class Demo6MultiThreadServer {
    public static void main(String[] ags) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost",8888));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector,SelectionKey.OP_ACCEPT);
        Worker worker = new Worker("worker-0");

        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    log.info("before connect...{}",sc.getRemoteAddress());
                    worker.register(sc);
                    log.info("after connect...{}",sc.getRemoteAddress());
                }
            }
        }
    }

   static class Worker implements Runnable{
        private Thread thread;
        private Selector selector;
        private String name;
        private ConcurrentLinkedDeque<Runnable> queue = new ConcurrentLinkedDeque<>();
        private volatile boolean start = false;

        public Worker(String name){
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if(!start){
                thread = new Thread(this,name);
                selector = Selector.open();
                thread.start();
                start = true;
            }
            selector.wakeup();
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true){
                selector.select();
                System.out.println("被唤醒了");
                Runnable task = queue.poll();
                if(task != null){
                    task.run();
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        SocketChannel channel = (SocketChannel) key.channel();
                        channel.read(buffer);
                        buffer.flip();
                        log.info("接收到的数据：{}", Charset.defaultCharset().decode(buffer));
                    }
                }
            }
        }
    }
}
