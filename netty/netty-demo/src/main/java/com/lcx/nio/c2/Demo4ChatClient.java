package com.lcx.nio.c2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Demo4ChatClient {

    public static void main(String[] args) throws IOException {
        InetSocketAddress address = new InetSocketAddress(8888);
        SocketChannel sc = SocketChannel.open(address);
        sc.configureBlocking(false);
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_READ);
        new Thread(new Read(selector)).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            if (message.length() > 0) {
                sc.write(Charset.forName(StandardCharsets.UTF_8.name()).encode(message));
            }
        }

    }

    public static class Read implements Runnable {

        Selector selector;

        Read(Selector selector) {
            this.selector = selector;
        }
        @Override
        public void run() {
            try {
                while (true) {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isReadable()) {
                            SocketChannel sc = (SocketChannel) selectionKey.channel();
                            ByteBuffer bf = ByteBuffer.allocate(1024);
                            StringBuilder message = new StringBuilder();
                            while (true) {
                                if (!(sc.read(bf) > 0)) break;
                                bf.flip();
                                message.append(Charset.forName(StandardCharsets.UTF_8.name()).decode(bf));
                                System.out.println(message);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
