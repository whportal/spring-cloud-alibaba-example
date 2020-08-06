package com.wh.product.feign;

import com.wh.common.response.Result;
import com.wh.product.feign.fallback.OrderFeignClientFallback;
import com.wh.product.feign.fallback.OrderFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * fallback 指定容错类
 * fallbackFactory 指定容错工厂
 * 若两个都配置 fallback 优先级更高 建议 两者只配置一个
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@FeignClient(value = "service-order",
        // fallback = OrderFeignClientFallback.class,
        fallbackFactory = OrderFeignClientFallbackFactory.class)
public interface OrderFeignClient {

    @GetMapping("/order/message1")
    Result<String> message1();
}
