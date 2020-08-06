package com.wh.mq.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * RocketMQ 消费者
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
// @Component
// @RocketMQMessageListener(consumerGroup = "wh-consumer-group", topic = "wh-test-topic")
// public class StringListener implements RocketMQListener<String> {
//     private static final Logger log = LoggerFactory.getLogger(StringListener.class);
//
//     @Override
//     public void onMessage(String message) {
//         log.info("接收到顺序消息-{}", message);
//     }
// }
