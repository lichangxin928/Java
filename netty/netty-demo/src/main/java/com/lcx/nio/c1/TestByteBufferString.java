package com.lcx.nio.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TestByteBufferString {
    public static void main(String[] args) {
        /**
         * 字符串转化为 ByteBuffer 的三种方式
         *  1. 直接用字符串put
         *  2. Charset StandardCharsets.UTF_8.encode(String)  decode 可以实现相反的效果
         *  3. ByteBuffer.warp(String)
         */
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("position:" + buffer.position() + "\tlimit:" + buffer.limit());
        buffer.put("hello".getBytes(StandardCharsets.UTF_8));
        System.out.println("position:" + buffer.position() + "\tlimit:" + buffer.limit());
        buffer.flip();
        System.out.println("position:" + buffer.position() + "\tlimit:" + buffer.limit());
        buffer.compact();
        System.out.println("position:" + buffer.position() + "\tlimit:" + buffer.limit());
    }
}
