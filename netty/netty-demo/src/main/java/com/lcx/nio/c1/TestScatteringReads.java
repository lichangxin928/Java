package com.lcx.nio.c1;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestScatteringReads {
    public static void main(String[] args) throws Exception {
        FileChannel channel = new RandomAccessFile("C:\\code\\Java\\netty\\netty-demo\\data.txt", "r").getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(3);
        ByteBuffer buffer2 = ByteBuffer.allocate(3);
        ByteBuffer buffer3 = ByteBuffer.allocate(5);
        channel.read(new ByteBuffer[]{buffer1,buffer2,buffer3});
        buffer1.flip();
        buffer2.flip();
        buffer3.flip();
        System.out.println((char) buffer1.get());
    }
}
