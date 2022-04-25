package com.lcx.nio.c1;

import java.nio.ByteBuffer;

public class TestByteBufferAllocate {

    public static void main(String[] args) {

        /**
         * class java.nio.HeapByteBuffer  java堆内存，读写效率较低，垃圾回收影响
         * class java.nio.DirectByteBuffer  直接内存，读写效率高（少一次拷贝）不受垃圾回收影响。分配内存效率低
         */
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
    }
}
