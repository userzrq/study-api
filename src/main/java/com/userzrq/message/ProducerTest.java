package com.userzrq.message;

import com.rabbitmq.client.ConnectionFactory;

/**
 * 逐一confirm模式
 */
public class ProducerTest {

    public static void main(String[] args) {

        String exchangeName = "confirmExchange";
        String queueName = "confirmQueue";
        // 消息的路由键和 绑定键保持一致
        String routingKey = "confirmRoutingKey";
        String bindingKey = "confirmRoutingKey";
        int count = 5;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.40.183");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        factory.setVirtualHost("/confirm");

        Sender sender = new Sender(factory, count, exchangeName, queueName, routingKey, bindingKey);

        sender.run();
    }
}
