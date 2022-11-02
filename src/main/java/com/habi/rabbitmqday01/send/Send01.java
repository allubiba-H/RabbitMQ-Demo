package com.habi.rabbitmqday01.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.server.ExportException;
import java.util.concurrent.TimeoutException;

public class Send01 {
    private static final String QUEUE_NAME = "hello";
    public static void main(String[] args) throws Exception {
        //创建MQ链接通道
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.122");
        try (Connection con = connectionFactory.newConnection();
             Channel channel = con.createChannel();){
            //声明队列
            channel.queueDeclare("simple",true,false,false,null);
            //发送消息
            channel.basicPublish("","simple",false, MessageProperties.PERSISTENT_TEXT_PLAIN,"你好".getBytes(StandardCharsets.UTF_8));
        }
    }
}
