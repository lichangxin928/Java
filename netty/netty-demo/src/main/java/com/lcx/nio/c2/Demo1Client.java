package com.lcx.nio.c2;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Demo1Client {
    public static void main(String[] args)  throws Exception{
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8888));
        sc.write(Charset.defaultCharset().encode("hi"));
        System.out.println("waiting....");

    }
}
