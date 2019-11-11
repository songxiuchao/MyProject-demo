package com.example.demo.RabbotMQ.constant;

/**
 * @program: ProjectCollection
 * @description: 队列名称
 * @author: xiuchao Song
 * @create: 2019-11-01 15:00
 **/
public class QueueConstant {
    //=========================队列=============================
    /**
     * 直连队列
     */
    public static final String directQueue="directQueue";

    /**
     * 订阅队列
     */
    public static final String TopicQueueMessage="topicQueueMessage";

    public static final String TopicQueueMessages="topicQueueMessages";

    /**
     * 广播队列
     */
    public static final String TopicFanoutA="topicFanout.A";

    public static final String TopicFanoutB="topicFanout.B";

    public static final String TopicFanoutC="topicFanout.C";

    //===============================队列结束=================================


    //===============================交换机开始================================
    /**
     * 直连交换机
     */
    public static final String DirectExchange="directExchange";

    /**
     * 订阅交换机
     */
    public static final String TopicExchange="topicExchange";

    /**
     * 广播交换机
     */
    public static final String FanoutExchange="fanoutExchange";

    //===============================交换机开结束================================

}
