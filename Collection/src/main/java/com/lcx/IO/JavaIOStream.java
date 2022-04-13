package com.lcx.IO;


import java.io.*;

public class JavaIOStream {
    /**
     * IoStream
     * 字节流和字符流
     * @param
     */

    public static void main(String[] args) throws Exception {
        File file = new File("hello.txt");
        File file1 = new File("hello1.txt");

        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        byte buf[] = new byte[4];
        int read;
        while ((read = fileInputStream.read(buf)) != -1){
            System.out.println(read);
            fileOutputStream.write(buf);
        }

    }

    private static void write(File file) throws IOException {
        FileWriter fw = new FileWriter(file,true);
//        fw.write("i have a dream");
        fw.append("a");
        // 只有关闭了才会显示到文件中去
        fw.close();
    }

    private static void readData(File file) throws IOException {
        System.out.println(file.getAbsolutePath());
        FileReader reader = new FileReader(file);
        int data;
//        while ((data = reader.read()) != -1){
//            System.out.print((char) data);
//        }

        char[] cbuf = new char[20];
        int len;
        while ((len = reader.read(cbuf)) != -1){
            for (int i = 0; i < len; i++) {
                System.out.print(cbuf[i]);
            }
        }
        reader.close();
    }
}
