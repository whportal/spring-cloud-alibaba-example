package com.wh.user.service.impl;

import com.google.common.collect.Maps;
import com.wh.common.response.Result;
import com.wh.dao.entity.Order;
import com.wh.dto.ProductDTO;
import com.wh.user.feign.OrderFeignClient;
import com.wh.user.feign.ProductFeignClient;
import com.wh.user.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@Service
public class UserServiceImpl implements UserService {

    private OrderFeignClient orderFeignClient;

    private ProductFeignClient productFeignClient;

    @Autowired
    public UserServiceImpl(OrderFeignClient orderFeignClient, ProductFeignClient productFeignClient) {
        this.orderFeignClient = orderFeignClient;
        this.productFeignClient = productFeignClient;
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public Order createOrder(Long pid) {
        Result<Order> result = orderFeignClient.createOrder(pid);
        Order order = result.getData();

        // 扣减商品库存
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPid(pid);
        productDTO.setNumber(order.getNumber());
        productFeignClient.reduceInventory(productDTO);
        return order;
    }
}
