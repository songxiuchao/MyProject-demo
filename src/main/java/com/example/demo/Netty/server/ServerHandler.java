package com.example.demo.Netty.server;

import com.example.demo.RabbotMQ.util.CustomerChannelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
/**
 * @program: demo
 * @description: Netty服务端处理器
 * @author: xiuchao Song
 * @create: 2019-11-02 13:54
 **/
@Component
@Sharable
@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 注入业务处理类
     */
   /* @Autowired
    private BusinessHandler businessHandler;*/

    /**
     * 客户端与服务端创建连接的时候调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.warn("收到客户端接入请求。客户端地址：" + channel.remoteAddress());
    }

    /**
     * 服务端处理客户端请求的核心方法，这里接收了客户端发来的信息
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        if (msg == null) {
            log.error("客户端：" + channel.remoteAddress() + "，发送空消息！");
            return;
        }
        //处理业务
        doMessage(msg);
        System.out.println("msg===========>"+msg);
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // ctx.flush();
    }

    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CustomerChannelManager.releaseChannel(channel);
        log.warn("收到客户端关闭请求。客户端地址：" + channel.remoteAddress());
    }

    /**
     * 服务出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    public void doMessage(Object msg){
        System.out.println(msg);
    }

}