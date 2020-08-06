package com.wh.user.controller;

import com.wh.common.response.Result;
import com.wh.dao.entity.Order;
import com.wh.user.feign.OrderFeignClient;
import com.wh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020/08/05
 */
@RestController
@RequestMapping("user")
public class UserController {

    private OrderFeignClient orderFeignClient;

    private UserService userService;

    @Autowired
    public UserController(OrderFeignClient orderFeignClient, UserService userService) {
        this.orderFeignClient = orderFeignClient;
        this.userService = userService;
    }

    @GetMapping("order/{pid}")
    public Result<Order> createOrder(@PathVariable("pid") Long pid) {
        return Result.success(userService.createOrder(pid));
    }
}
