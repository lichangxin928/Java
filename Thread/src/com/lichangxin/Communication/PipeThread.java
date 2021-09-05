package com.lichangxin.Communication;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeThread {
//    在 java.io.PipeStream 中管道流用于在线程之间传送数据，一个线程发生数据到输出管道
//    另一个线程从管道中读取数据 包括 PipeInputStream 和 PipeOutputStream PipedReader PipeWriter
    // 使用 pipeInputStream 和pipeOutputStream

    // 定义方法向管道流中写入数据
    public static void writeData(PipedOutputStream out) {
        try {
            for (int i = 0; i < 100; i++) {
                String data = "" + i;
                out.write(data.getBytes());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 定义方法从管道流中读取数据
    public static void readData(PipedInputStream in) {
        byte[] bytes = new byte[1024];

        try {
            int len = in.read(bytes);
            while (len != -1) {
                System.out.println(new String(bytes, 0, len));
                len = in.read(bytes);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        // 建立连接
        pipedInputStream.connect(pipedOutputStream);

        new Thread(new Runnable() {
            @Override
            public void run() {
                writeData(pipedOutputStream);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readData(pipedInputStream);
            }
        }).start();
    }
}
