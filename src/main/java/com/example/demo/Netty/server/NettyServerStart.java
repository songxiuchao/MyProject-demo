package com.example.demo.Netty.server;

import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: netty测试类
 * @author: xiuchao Song
 * @create: 2019-11-02 14:04
 **/
@Slf4j
@RestController
@RequestMapping("/netty")
public class NettyServerStart {
    /**
     * NettyServer_URL
     */
    @Value("${netty.server.url}")
    private  String url="127.0.0.1";

    /**
     * NettyServer_PORT
     */
    @Value("${netty.server.port}")
    private  int port=16001;


    @Autowired
    private  NettyServer socketServer;

    /**
     * 启动服务器端
     * @throws Exception
     */
    @PostMapping("/startServer")
    public void run() throws Exception {
        ChannelFuture future = socketServer.start(url,port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                socketServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
