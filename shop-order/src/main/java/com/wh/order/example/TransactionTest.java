package com.wh.order.example;

import com.wh.dao.entity.Order;
import com.wh.dao.entity.TxLog;
import com.wh.order.mapper.OrderMapper;
import com.wh.order.mapper.TxLogMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
@Component
public class TransactionTest {
    private OrderMapper orderMapper;
    private TxLogMapper txLogMapper;
    private RocketMQTemplate rocketMQTemplate;
    private final transient LongAdder longAdder = new LongAdder();

    @Autowired
    public TransactionTest(OrderMapper orderMapper, TxLogMapper txLogMapper, RocketMQTemplate rocketMQTemplate) {
        this.orderMapper = orderMapper;
        this.txLogMapper = txLogMapper;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void createOrderBefore(Order order) {
        longAdder.increment();
        Long txId = longAdder.longValue();

        // 发送半事务消息
        rocketMQTemplate.sendMessageInTransaction("tx-topic", MessageBuilder.withPayload(order).setHeader("txId", txId).build(), "其他参数");
    }


    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Long txId, Order order) {

        // 本地事务
        orderMapper.save(order);

        // 记录日志到数据库，回查使用
        TxLog txLog = new TxLog();
        txLog.setTxLogId(txId);
        txLog.setContent("事务测试");
        txLog.setGmtCreate(LocalDateTime.now());
        txLogMapper.save(txLog);
    }
}
