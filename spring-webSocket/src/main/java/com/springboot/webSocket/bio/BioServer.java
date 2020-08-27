package com.springboot.webSocket.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : yanwenhui
 * @description : Bio 服务端
 * @date : 2020/8/27
 */
public class BioServer {

    private final static int PORT = 1020;

    public static void main(String[] args) throws IOException {
        startServer();
    }

    public static void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("BIO服务器已启动，端口号：" + PORT);
        while (true) {

            // 此方法会阻塞
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(() -> {
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    InputStream inputStream = socket.getInputStream();

                    System.out.println("连接！");
                    printWriter.println("欢迎登陆到BIO服务器！port：1020");
                    printWriter.flush();

                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    String info = "";

                    // 此方法会阻塞， 直到客户端close outputStream, br.readLine()才为null，否则一直阻塞获取消息
                    while(null != (info = br.readLine())) {
                        System.out.println("客户端说：" + info);
                    }

                    System.out.println("断开连接！");
                    printWriter.println("断开连接！");
                    printWriter.flush();

                    br.close();
                    inputStream.close();
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            thread.start();


        }
    }

}
