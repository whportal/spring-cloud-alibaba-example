package com.wh.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wh.common.response.Result;
import com.wh.dao.entity.Order;
import com.wh.dao.entity.Product;
import com.wh.order.fallback.OrderBlockHandler;
import com.wh.order.fallback.OrderFallback;
import com.wh.order.feign.ProductFeignClient;
import com.wh.order.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@RestController
@RequestMapping("order")
public class OrderController {

    private ProductFeignClient productFeignClient;

    private OrderService orderService;

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public OrderController(ProductFeignClient productFeignClient, OrderService orderService, RocketMQTemplate rocketMQTemplate) {
        this.productFeignClient = productFeignClient;
        this.orderService = orderService;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @PostMapping("/prod/{pid}")
    public Result<Order> saveOrder(@PathVariable("pid") Long pid) {
        // 调用商品服务获取数据
        Result<Product> result = productFeignClient.findById(pid);
        Product product = result.getData();

        // 构建订单数据
        Order order = new Order();
        order.setUid(1L);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setName(product.getName());
        order.setPrice(product.getPrice());
        order.setNumber(1);
        order.setGmtCreate(LocalDateTime.now());

        // 保存数据
        return Result.success(orderService.save(order));
    }

    @GetMapping("message1")
    public Result<String> message1() {
        return Result.success("message1");
    }

    @GetMapping("message2")
    public Result<String> message2() {
        return Result.success("message2");
    }

    @GetMapping("message3")
    @SentinelResource(
            value = "message3",
            // 指定了降级及异常处理方法后，全局 Sentinel 异常处理就捕获不到此处的异常
            blockHandler = "message3BlockHandler", // 指定发生 BlockException 时进入的方法
            blockHandlerClass = OrderBlockHandler.class,// 指定 限流降级 类
            fallback = "message3Fallback", // 指定发生 Throwable 时进入的方法
            fallbackClass = OrderFallback.class // 指定 Fallback 类
    )
    public Result<String> message3(String name, Integer age) {
        int i = new Random().nextInt(3);
        int j = 10 / i;
        return Result.success(name + age);
    }

    @GetMapping("mq/message")
    public Result<String> mqMessage() {
        rocketMQTemplate.convertAndSend("order-topic", "这是 RocketMQ 发送的第一条消息");
        return Result.success();
    }

    @PostMapping("/create/{pid}")
    public Result<Order> createOrder(@PathVariable("pid") Long pid) {
        // 保存数据
        return Result.success(orderService.createOrder(pid));
    }
}
