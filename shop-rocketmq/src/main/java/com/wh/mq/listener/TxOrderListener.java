package com.wh.mq.listener;

import com.alibaba.fastjson.JSON;
import com.wh.dao.entity.Order;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
@Component
@RocketMQMessageListener(consumerGroup = "wh-tx-consumer-group", topic = "tx-topic", consumeMode = ConsumeMode.ORDERLY)
public class TxOrderListener implements RocketMQListener<Order> {
    private static final Logger log = LoggerFactory.getLogger(TxOrderListener.class);

    @Override
    public void onMessage(Order order) {
        log.info("接收到事务消息-{}", order);
    }
}
