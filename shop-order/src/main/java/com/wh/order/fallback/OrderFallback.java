package com.wh.order.fallback;

import com.wh.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Sentinel 限流降级方法异常处理类</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-28
 */
public class OrderFallback {

    private static final Logger log = LoggerFactory.getLogger(OrderFallback.class);

    /**
     * Sentinel 限流降级方法异常的处理
     * 必须是 static 修饰
     * 返回值必须和被处理的方法一致
     * 参数必须和被处理的方法一致
     */
    public static Result<String> message3Fallback(String name, Integer age, Throwable t) {
        log.error("进入了 Sentinel 限流降级方法异常处理的方法逻辑.[name:{},age:{},message:{}]", name, age, t.getMessage(), t);
        return Result.failed(t.getMessage());
    }
}
