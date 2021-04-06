package com.userzrq.message;

import com.rabbitmq.client.ConnectionFactory;

/**
 * 批量confirm模式
 */
public class ProducerBatchTest {

    public static void main(String[] args) {

        String exchangeName = "confirmExchange";
        String queueName = "confirmQueue";
        String routingKey = "confirmRoutingKey";
        String bindingKey = "confirmRoutingKey";
        int count = 100;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.40.183");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        factory.setVirtualHost("/confirm");

        BatchSender producer = new BatchSender(factory, count, exchangeName, queueName, routingKey, bindingKey);

        producer.run();
    }
}
