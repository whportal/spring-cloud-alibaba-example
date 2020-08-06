package com.wh.order.controller;

import com.wh.common.response.Result;
import com.wh.dao.entity.Order;
import com.wh.order.example.TransactionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
@RestController
@RequestMapping("mq")
public class TransactionController {
    private TransactionTest transactionTest;

    @Autowired
    public TransactionController(TransactionTest transactionTest) {
        this.transactionTest = transactionTest;
    }

    @GetMapping("order")
    public Result<Order> createOrderWithMQ() {

        Order order = new Order();
        order.setOid(888L);
        order.setUsername("事务消息");
        transactionTest.createOrderBefore(order);
        return Result.success(order);
    }
}
