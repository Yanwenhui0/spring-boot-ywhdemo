package com.springboot.webSocket.netty.server;

import com.alibaba.fastjson.JSON;
import com.springboot.webSocket.netty.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :  yanwenhui
 * @date : 2020/7/6
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * name-ChannelId
     */
    private static final ConcurrentHashMap<String, ChannelId> NAME_MAP = new ConcurrentHashMap<>();
    /**
     * 群发
     */
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /**
     * 管理一个全局map，保存连接进服务端的通道数量
     */
    private static final ConcurrentHashMap<ChannelId, ChannelHandlerContext> CHANNEL_MAP = new ConcurrentHashMap<>();



    /**
     * @param ctx
     * @DESCRIPTION: 有客户端连接服务器会触发此函数
     * @return: void
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();

        String clientIp = insocket.getAddress().getHostAddress();
        int clientPort = insocket.getPort();

        //获取连接通道唯一标识
        ChannelId channelId = ctx.channel().id();
        System.out.println();
        //如果map中不包含此连接，就保存连接
        if (CHANNEL_MAP.containsKey(channelId)) {
            System.out.println("客户端【" + channelId + "】是连接状态，连接通道数量: " + CHANNEL_MAP.size());
        } else {
            //保存连接
            NAME_MAP.put(channelId.toString(), channelId);
            CHANNEL_MAP.put(channelId, ctx);
            clients.add(ctx.channel());

            System.out.println("客户端【" + channelId + "】连接netty服务器[IP:" + clientIp + "--->PORT:" + clientPort + "]");
            System.out.println("连接通道数量: " + CHANNEL_MAP.size());
        }

        String msg = "当前在线人数(如下)：" + CHANNEL_MAP.size() + "\n";
        Enumeration<ChannelId> keys = CHANNEL_MAP.keys();
        while (keys.hasMoreElements()) {
            msg += "\t" + keys.nextElement() + "\n";
        }
        ctx.writeAndFlush(msg);
    }

    /**
     * @param ctx
     * @DESCRIPTION: 有客户端终止连接服务器会触发此函数
     * @return: void
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();

        String clientIp = insocket.getAddress().getHostAddress();

        ChannelId channelId = ctx.channel().id();

        if (NAME_MAP.containsKey(channelId.toString())) {
            //删除连接
            NAME_MAP.remove(channelId.toString());
        }
        //包含此客户端才去删除
        if (CHANNEL_MAP.containsKey(channelId)) {
            //删除连接
            CHANNEL_MAP.remove(channelId);
            clients.remove(ctx.channel());
            System.out.println();
            System.out.println("客户端【" + channelId + "】退出netty服务器[IP:" + clientIp + "--->PORT:" + insocket.getPort() + "]");
            System.out.println("连接通道数量: " + CHANNEL_MAP.size());
        }
    }

    /**
     * @param ctx
     * @DESCRIPTION: 有客户端发消息会触发此函数
     * @return: void
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println();
        System.out.println("加载客户端报文......");
        System.out.println("【" + ctx.channel().id() + "】" + " :" + msg.toString());

        /**
         *  下面可以解析数据，保存数据，生成返回报文，将需要返回报文写入write函数
         *
         */
        Message message = JSON.parseObject(msg.toString(), Message.class);
        System.out.println(message);
        //响应客户端
        this.channelWrite(ctx, message);
    }

    /**
     * @param msg        需要发送的消息内容
     * @DESCRIPTION: 服务端给客户端发送消息
     * @return: void
     */
    public void channelWrite(ChannelHandlerContext from, Message msg) throws Exception {
        System.out.println(msg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(msg.getType() == 0){
            clients.writeAndFlush(sdf.format(new Date()) + " ==> " + msg.getFromUser().getName() + "(群发):"+ msg.getMessage());
        }else{
            ChannelId channelId = NAME_MAP.get(msg.getToUser().getName());
            ChannelHandlerContext ctx = CHANNEL_MAP.get(channelId);
            if (ctx == null) {
                from.writeAndFlush(sdf.format(new Date()) + " ==> " + "系统提示（用户：" + msg.getToUser().getName() + "不在线！)");
                return;
            }
            if (StringUtils.isBlank(msg.getMessage())) {
                from.writeAndFlush(sdf.format(new Date()) + " ==> " + "服务端响应空的消息");
                return;
            }
            from.writeAndFlush(sdf.format(new Date()) + " ==> " + "你对" + msg.getToUser().getName() + "说：" + msg.getMessage());
            ctx.writeAndFlush(sdf.format(new Date()) + " ==> " + msg.getFromUser().getName() + "对我说：" + msg.getMessage());

        }
    }


    /**
     * 在initChannel中进行如下设置：
     *      ch.pipeline().addLast(new IdleStateHandler(1, 5, 1, TimeUnit.SECONDS));
     *      设置一下服务端读的空闲时间，例如5s
     *      如果五秒内ChannelRead()方法未被调用则触发一次userEventTrigger()方法
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        String socketString = ctx.channel().remoteAddress().toString();

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("Client: " + socketString + " READER_IDLE 读超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("Client: " + socketString + " WRITER_IDLE 写超时");
                ctx.disconnect();
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("Client: " + socketString + " ALL_IDLE 总超时");
                ctx.disconnect();
            }
        }
    }

    /**
     * @param ctx
     * @DESCRIPTION: 发生异常会触发此函数
     * @return: void
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println();
        ctx.close();
        System.out.println(ctx.channel().id() + " 发生了错误,此连接被关闭" + "此时连通数量: " + CHANNEL_MAP.size());
        //cause.printStackTrace();
    }
}