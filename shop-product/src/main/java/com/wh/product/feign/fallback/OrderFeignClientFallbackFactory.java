package com.wh.product.feign.fallback;

import com.wh.product.feign.OrderFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 这是容错类,他要求我们要是实现一个FallbackFactory<要为哪个接口产生容错类>
 * 可以通过容错工厂方式获取错误信息
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@Component
public class OrderFeignClientFallbackFactory implements FallbackFactory<OrderFeignClient> {

    private static final Logger log = LoggerFactory.getLogger(OrderFeignClientFallbackFactory.class);

    private OrderFeignClientFallback orderFeignClientFallback;

    @Autowired
    public OrderFeignClientFallbackFactory(OrderFeignClientFallback orderFeignClientFallback) {
        this.orderFeignClientFallback = orderFeignClientFallback;
    }

    @Override
    public OrderFeignClient create(Throwable throwable) {
        System.err.println("==============进入了 OrderFeignClientFallbackFactory 方法==============");
        log.error("Product 使用 feign 调用 Order 服务发生了异常.", throwable);
        return orderFeignClientFallback;
    }
}
