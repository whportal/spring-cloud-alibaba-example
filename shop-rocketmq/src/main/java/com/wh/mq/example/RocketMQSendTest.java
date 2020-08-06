package com.wh.mq.example;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * <p>
 * RocketMQ 快速入门
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
public class RocketMQSendTest {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 1.创建消息生产者，指定生产者所属的组
        DefaultMQProducer producer = new DefaultMQProducer("wh-producer-group");

        // 2.指定 NameServer 地址
        producer.setNamesrvAddr("192.168.10.252:9876");

        // 3.启动生产者
        producer.start();

        // 4.创建消息对象，指定主题、标签和消息体
        Message message = new Message("wh-topic", "wh-tag", "RocketMQ Message 第一条".getBytes());

        // 5.发送消息
        SendResult result = producer.send(message, 10000);
        System.err.println("发送消息结果：" + result);

        // 6.关闭生产者
        producer.shutdown();
    }
}
