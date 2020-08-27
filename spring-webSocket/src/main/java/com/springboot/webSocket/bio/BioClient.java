package com.springboot.webSocket.bio;

import com.sun.org.apache.bcel.internal.generic.IXOR;
import org.apache.commons.lang3.StringUtils;

import javax.xml.ws.Holder;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/27
 */
public class BioClient {

    private final static int PORT = 1020;
    private final static String HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        send();
    }

    private static void send() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

        String info = "";
        byte[] infoByte = new byte[1024];
        inputStream.read(infoByte);
        info = new String(infoByte);
        System.out.println("服务器说：" + info);

        System.out.println("请输入信息");
        Scanner input = new Scanner(System.in);
        String msg = "";
        while (StringUtils.isNotBlank(msg = input.nextLine())) {
            printWriter.println(msg);
            printWriter.flush();
        }

        System.out.println("输入结束");
        printWriter.close();
        bufferedReader.close();


    }

}
