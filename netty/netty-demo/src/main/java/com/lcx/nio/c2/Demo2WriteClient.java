package com.lcx.nio.c2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Demo2WriteClient {
    public static void main(String[] args)  throws Exception{
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8081));

        int count = 0;
        while (true){
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
            count += sc.read(buffer);
            System.out.println(count);
            buffer.clear();
        }
    }
}
