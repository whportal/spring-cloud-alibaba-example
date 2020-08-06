// package com.wh.user.message;
//
// import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
// import org.apache.rocketmq.spring.core.RocketMQListener;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
//
// /**
//  * <p>
//  *     RocketMQ 消息的消费者
//  * </p>
//  *
//  * @author Wenhao Wang
//  * @version 1.0
//  * @date 2020-08-03
//  */
// @Service
// @RocketMQMessageListener(consumerGroup = "shop-user",topic = "order-topic")
// public class SmsListener implements RocketMQListener<String> {
//     private static final Logger log = LoggerFactory.getLogger(SmsListener.class);
//
//     @Override
//     public void onMessage(String s) {
//         log.info("接收到消息：[message：{}]", s);
//     }
// }
