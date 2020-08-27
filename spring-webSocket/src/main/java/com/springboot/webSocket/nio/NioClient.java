package com.springboot.webSocket.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/27
 */
public class NioClient {

    private final static int PORT = 1020;
    private final static String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        send();
    }

    private static void send() throws IOException {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        // 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
        socketChannel.configureBlocking(false); // 开启非阻塞模式

        if (socketChannel.connect(new InetSocketAddress(HOST, PORT))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        while (true) {
            int wait = selector.select(1000);
            if (wait == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIter = selectionKeys.iterator();

            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                if (key.isValid()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    if (key.isConnectable()) {
                        System.out.println("key.isConnectable()");
                        if (sc.finishConnect()) {
                            sc.register(selector, SelectionKey.OP_READ);
                            doWrite(sc);
                        } else {
                            System.exit(1);
                        }
                    }
                    if (key.isReadable()) {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        if (readBytes > 0) {
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            String body = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println("res:" + body);
                        } else if (readBytes < 0) {
                            key.cancel();
                            sc.close();
                        }

                    }
                }

                keyIter.remove();
            }
        }

    }

    private static void doWrite(SocketChannel socketChannel) throws IOException {
        Scanner input = new Scanner(System.in);
        String msg;
        while (StringUtils.isNotBlank(msg = input.nextLine())) {
            // 将消息编码为字节数组
            byte[] request = msg.getBytes();
            // 根据数组容量创建ByteBuffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(request.length);
            // 将字节数组复制到缓冲区
            writeBuffer.put(request);
            // flip读写切换操作
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            if (!writeBuffer.hasRemaining()) {
                System.out.println("写入完成");
            }
        }
    }

}
