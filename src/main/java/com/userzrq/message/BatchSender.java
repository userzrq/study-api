package com.userzrq.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

/**
 * 开启信道的批量commit模式
 */
public class BatchSender {

    private ConnectionFactory factory;
    private int count;
    private String exchangeName;
    private String queueName;
    private String routingKey;
    private String bindingKey;

    public BatchSender(ConnectionFactory factory, int count, String exchangeName, String queueName, String routingKey, String bindingKey) {
        this.factory = factory;
        this.count = count;
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.bindingKey = bindingKey;
    }

    public void run() {
        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            // 创建信道
            channel = connection.createChannel();

            channel.queueDeclare(queueName, true, false, false, null);

            channel.exchangeDeclare(exchangeName, ExchangeTypes.DIRECT, true, false, null);

            channel.queueBind(queueName, exchangeName, bindingKey);

            channel.confirmSelect();

            for (int i = 0; i < count; i++) {
                //第一个参数是exchangeName(默认情况下代理服务器端是存在一个""名字的exchange的,
                //因此如果不创建exchange的话我们可以直接将该参数设置成"",如果创建了exchange的话
                //我们需要将该参数设置成创建的exchange的名字),第二个参数是路由键
                channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_BASIC, ("第" + (i + 1) + "条消息").getBytes());
            }
            final long start = System.currentTimeMillis();
            channel.addConfirmListener(new ConfirmListener() {

                // 重写mq 成功接收到消息的回调方法
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("ack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
                }

                // 重写mq 接收消息失败的回调方法
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("nack: deliveryTag = " + deliveryTag + " multiple: " + multiple);
                }
            });

            channel.waitForConfirmsOrDie();
            System.out.println("执行waitForConfirmsOrDie耗费时间: " + (System.currentTimeMillis() - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
