package com.lcx.nio.c1;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestScatteringWrite {
    public static void main(String[] args) throws Exception {

        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer buffer3 = StandardCharsets.UTF_8.encode("你好");

        FileChannel channel = new RandomAccessFile("hello.txt","rw").getChannel();

        channel.write(new ByteBuffer[]{buffer1,buffer2,buffer3});

    }
}
