package com.example.demo.Netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: Netty服务器初始化
 * @author: xiuchao Song
 * @create: 2019-11-02 13:51
 **/
@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 增加任务处理
        ChannelPipeline pipeline = ch.pipeline();

        // 定义字符分割
        ByteBuf delimiter = Unpooled.copiedBuffer(Config.MSG_END_FLAG.getBytes());

        // 解码编码
        pipeline.addLast(new DelimiterBasedFrameDecoder(1014, delimiter))
                .addLast(new StringDecoder(CharsetUtil.UTF_8))
                .addLast(new StringEncoder(CharsetUtil.UTF_8))
                .addLast(serverHandler);
    }

}