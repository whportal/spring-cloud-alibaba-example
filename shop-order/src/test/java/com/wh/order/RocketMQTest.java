package com.wh.order;

import com.wh.dao.entity.Order;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * RocketMQ 发送消息测试
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-08-03
 */
@SpringBootTest(classes = OrderApplication.class)
@RunWith(SpringRunner.class)
public class RocketMQTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步消息
     */
    @Test
    public void testSyncMessage() {
        /*
        参数1：topic 如果想添加 tag，可以使用 topic:tag 的写法
        参数2：消息内容
         */

        SendResult result = rocketMQTemplate.syncSend("wh-test-topic", "这是一条同步消息");
        System.out.println(result);
    }

    /**
     * 异步消息
     */
    @Test
    public void testAsyncMessage() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        rocketMQTemplate.asyncSend("wh-test-topic", "这是一条异步消息", new SendCallback() {
             @Override
             public void onSuccess(SendResult sendResult) {
                 System.out.println(sendResult);
                 countDownLatch.countDown();
             }

             @Override
             public void onException(Throwable throwable) {
                 System.out.println(throwable);
                 countDownLatch.countDown();
             }
         });

        System.out.println("发送异步消息成功==========");
        countDownLatch.await();
    }

    /**
     * 单向消息
     */
    @Test
    public void testOneWayMessage() {
        rocketMQTemplate.sendOneWay("wh-test-topic", "这是一条单向消息");
    }

    /**
     * 同步顺序消息[异步顺序消息、单向异步消息写法类似]
     */
    @Test
    public void testSyncOrderly() {
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setOid((long) i);
            order.setUsername("同步顺序消息---" + i);
            rocketMQTemplate.syncSendOrderly("wh-test-topic", order, "oid");
        }

    }
}
