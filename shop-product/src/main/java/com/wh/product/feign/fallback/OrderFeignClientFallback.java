package com.wh.product.feign.fallback;

import com.wh.common.response.Result;
import com.wh.product.feign.OrderFeignClient;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 这是一个容错类
 * 它要求实现Feign所在接口,并实现里面的方法
 * 当feign调用出现问题的时候,就会进入到当前类中同名方法中
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@Component
public class OrderFeignClientFallback implements OrderFeignClient {

    @Override
    public Result<String> message1() {
        System.err.println("==============进入了 OrderFeignClientFallback 方法==============");
        return Result.success("我是 Sentinel 对 feign 支持的容错方法");
    }
}
