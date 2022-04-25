package com.lcx.nio.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TestFileChannelTransfer {

    public static void main(String[] args) throws Exception{
        final FileChannel from = new FileInputStream("hello.txt").getChannel();
        final FileChannel to = new FileOutputStream("to.txt").getChannel();

        // 操作系统的零拷贝,最大支持 2g/每次
        from.transferTo(0,from.size(),to);

    }
}
