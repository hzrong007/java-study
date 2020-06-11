package com.rw.netty.service.jdk;

import com.rw.netty.service.CloseUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyNIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress("127.0.0.1", 9092));

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            handle(selector);
        }
    }

    private static void handle(Selector selector) throws IOException {
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isWritable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer wrap = ByteBuffer.wrap("hello world".getBytes());
                channel.write(wrap);
                CloseUtil.close(channel);
            }
        }
    }
}
