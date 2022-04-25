package com.lcx.nio.c1;

import java.nio.ByteBuffer;

public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(16);
        buf.put(new byte[]{'a','b','c'});
        buf.flip();
        byte[] bytes = new byte[3];
        buf.get(bytes);
        buf.rewind();
        for(byte str : bytes){
            System.out.println(str);
        }
    }
}
