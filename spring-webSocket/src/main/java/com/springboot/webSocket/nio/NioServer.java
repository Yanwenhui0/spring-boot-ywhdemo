package com.springboot.webSocket.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/27
 */
public class NioServer {

    private final static int PORT = 1020;

    // 超时时间,单位毫秒
    private static final int TIME_OUT = 3000;

    public static void main(String[] args) throws IOException {
        startServer();
    }

    public static void startServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("NIO服务器已启动，端口号：" + PORT);
        // 创建一个选择器并将serverChannel注册到它上面
        Selector selector = Selector.open();
        //设置为客户端请求连接时，默认客户端已经连接上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            // 轮询监听key，select是阻塞的，accept()也是阻塞的
            if (selector.select(TIME_OUT) == 0) {
                System.out.println(".");
                continue;
            }

            // 有客户端请求，被轮询监听到
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                if (key.isAcceptable()) {
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    //意思是在通过Selector监听Channel时对读事件感兴趣
                    clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                else if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    // 接下来是java缓冲区io操作，避免io堵塞
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    buffer.clear();
                    long bytesRead = clientChannel.read(buffer);
                    if (bytesRead == -1) {
                        // 没有读取到内容的情况
                        clientChannel.close();
                    } else {
                        // 将缓冲区准备为数据传出状态
                        buffer.flip();
                        // 将获得字节字符串(使用Charset进行解码)
                        String receivedString = StandardCharsets.UTF_8.newDecoder().decode(buffer).toString();
                        System.out.println("接收到信息:" + receivedString);
                        String sendString = "你好,客户端. 已经收到你的信息:" + receivedString;
                        buffer = ByteBuffer.wrap(sendString.getBytes(StandardCharsets.UTF_8));

                        clientChannel.write(buffer);
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                }
            }
            keyIter.remove();
        }
    }

}
