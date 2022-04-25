package com.lcx.nio.c1;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    public static void main(String[] args) throws IOException {

        // FileChannel
        // 1. 输入输出流
        FileChannel channel = new FileInputStream("C:\\code\\Java\\netty\\netty-demo\\data.txt").getChannel();
        // 准备缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 读取
        int len = 0;
        while ((len = channel.read(buffer)) != -1){
            // 打印 buffer 的内容
            buffer.flip(); // 切换到读模式
            while (buffer.hasRemaining()){
                byte b = buffer.get();
                System.out.print((char)b);
            }
            buffer.compact();
        }
    }
}
