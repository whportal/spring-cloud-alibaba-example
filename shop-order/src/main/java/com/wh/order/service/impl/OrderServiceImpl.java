package com.wh.order.service.impl;

import com.wh.common.response.Result;
import com.wh.dao.entity.Order;
import com.wh.dao.entity.Product;
import com.wh.dto.ProductDTO;
import com.wh.order.feign.ProductFeignClient;
import com.wh.order.mapper.OrderMapper;
import com.wh.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@Service
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    private ProductFeignClient productFeignClient;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, ProductFeignClient productFeignClient) {
        this.orderMapper = orderMapper;
        this.productFeignClient = productFeignClient;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order save(Order order) {

        return orderMapper.save(order);
    }

    // @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order createOrder(Long pid) {
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
        order.setNumber(100);
        order.setGmtCreate(LocalDateTime.now());
        this.save(order);

        // // 扣减商品库存
        // ProductDTO productDTO = new ProductDTO();
        // productDTO.setPid(pid);
        // productDTO.setNumber(order.getNumber());
        //
        // CompletableFuture.runAsync(() -> productFeignClient.reduceInventory(productDTO));

        return order;
    }
}
