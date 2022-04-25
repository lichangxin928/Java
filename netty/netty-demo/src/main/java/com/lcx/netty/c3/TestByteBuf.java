package com.lcx.netty.c3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class TestByteBuf {
    public static void main(String[] args) {

        // 自动扩容、直接内存和堆内存、池化技术
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();

    }
}
