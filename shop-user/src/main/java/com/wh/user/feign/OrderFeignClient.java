package com.wh.user.feign;

import com.wh.common.response.Result;
import com.wh.dao.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020/08/05
 */
@FeignClient(value = "service-order")
public interface OrderFeignClient {

    @PostMapping("order/create/{pid}")
    Result<Order> createOrder(@PathVariable("pid") Long pid);
}
