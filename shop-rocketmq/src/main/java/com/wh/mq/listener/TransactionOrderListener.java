package com.wh.mq.listener;

import com.wh.dao.entity.Order;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
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
@Component
@RocketMQMessageListener(consumerGroup = "wh-tx-order", topic = "wh-test-topic", consumeMode = ConsumeMode.ORDERLY)
public class TransactionOrderListener implements RocketMQListener<Order> {
    private static final Logger log = LoggerFactory.getLogger(TransactionOrderListener.class);

    @Override
    public void onMessage(Order order) {
        log.info("接收到顺序消息-{}", order);
    }
}
