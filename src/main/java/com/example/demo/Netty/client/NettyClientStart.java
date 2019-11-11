package com.example.demo.Netty.client;

import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: netty客户端启动
 * @author: xiuchao Song
 * @create: 2019-11-02 14:22
 **/
@Slf4j
@RestController
@RequestMapping("/netty")
public class NettyClientStart {
    /**
     * NettyServer_URL
     */
    @Value("${netty.server.url}")
    private  String url="127.0.0.1";

    /**
     * NettyServer_PORT
     */
    //@Value("${netty.server.port}")
    private  int port=16002;


    @Autowired
    private  NettyClient nettyClient;


    /**
     * 启动客户端
     * @throws Exception
     */
    @PostMapping("/startClient")
    public  void run() throws Exception {
        ChannelFuture future = nettyClient.start(url,port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                nettyClient.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
