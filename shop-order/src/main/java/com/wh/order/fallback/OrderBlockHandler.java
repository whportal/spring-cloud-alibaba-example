package com.wh.order.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wh.common.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Sentinel 限流和降级 处理类</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-28
 */
public class OrderBlockHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderBlockHandler.class);

    /**
     * Sentinel 限流降级方法
     * 必须为 static 方法
     * 返回值以及参数需要和被限流降级的方法一致
     */
    public static Result<String> message3BlockHandler(String name, Integer age, BlockException e) {
        log.error("进入了 Sentinel 限流降级方法处理的方法逻辑.[name:{},age:{},message:{}]", name, age, e.getMessage(), e);
        return Result.failed(e.getMessage());
    }
}
