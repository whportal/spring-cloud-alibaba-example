package com.wh.order.example;

import com.wh.dao.entity.Order;
import com.wh.dao.entity.TxLog;
import com.wh.order.mapper.TxLogMapper;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * RocketMQ 事务消息发送
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
@Service
@RocketMQTransactionListener
public class TransactionMessageService implements RocketMQLocalTransactionListener {
    private TxLogMapper txLogMapper;
    private TransactionTest transactionTest;

    @Autowired
    public TransactionMessageService(TxLogMapper txLogMapper,TransactionTest transactionTest) {
        this.txLogMapper = txLogMapper;
        this.transactionTest = transactionTest;
    }

    /**
     * 执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        long txId = (long) msg.getHeaders().get("txId");

        try {
            // 本地事务
            transactionTest.createOrder(txId,(Order) msg);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;

        }
    }

    /**
     * 消息回查
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        long txId = (long) msg.getHeaders().get("txId");
        TxLog txLog = txLogMapper.findById(txId).orElse(null);
        if (Objects.nonNull(txLog)) {
            // 本地事务成功
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
