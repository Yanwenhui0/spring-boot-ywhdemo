package com.springboot.webSocket.netty.client;

import com.alibaba.fastjson.JSON;
import com.springboot.webSocket.netty.Message;
import com.springboot.webSocket.netty.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author :  yanwenhui
 * @date : 2020/7/6
 */
public class NettyClient{

    private static String URL="127.0.0.1";

    private static int PORT=9528;

    private static int SIZE = 512;

    public void sendMessage() throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyClientChannelInitializer() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture future = b.connect(URL, PORT).sync();
            System.out.println("0:群聊，1：单聊");
            Scanner input = new Scanner(System.in);
            int choose = input.nextInt();
            if(choose == 1) {
                while (true) {
                    System.out.println("请选择好友：");
                    String to = input.next();
                    System.out.println("===开始聊天===(输入back返回，重新选择好友，输入exit，退出重新)");
                    while (true) {
                        String text = input.next();
                        if (StringUtils.equalsIgnoreCase("back", text)) {
                            break;
                        }
                        if (StringUtils.equalsIgnoreCase("exit", text)) {
                            return;
                        }
                        Message build = Message.builder()
                                .withFromUser(new User(future.channel().id().toString()))
                                .withToUser(new User(to))
                                .withType(1).withMessage(text)
                                .build();
                        future.channel().writeAndFlush(JSON.toJSONString(build));
                    }
                }
            } else {
                System.out.println("===开始聊天===");
                while (true) {
                    String text = input.next();
                    if (StringUtils.equalsIgnoreCase("back", text)) {
                        break;
                    }
                    if (StringUtils.equalsIgnoreCase("exit", text)) {
                        return;
                    }
                    Message build = Message.builder()
                            .withFromUser(new User(future.channel().id().toString()))
                            .withType(0).withMessage(text)
                            .build();
                    future.channel().writeAndFlush(JSON.toJSONString(build));
                }
            }


        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        NettyClient nettyClient = new NettyClient();
        nettyClient.sendMessage();
    }

}