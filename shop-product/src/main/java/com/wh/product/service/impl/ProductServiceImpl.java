package com.wh.product.service.impl;

import com.wh.common.response.Result;
import com.wh.dao.entity.Product;
import com.wh.product.feign.OrderFeignClient;
import com.wh.product.mapper.ProductMapper;
import com.wh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    private OrderFeignClient orderFeignClient;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, OrderFeignClient orderFeignClient) {
        this.productMapper = productMapper;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public Product findByPid(Long pid) {
        return productMapper.findById(pid).orElse(null);
    }

    @Override
    public String getMessageFromOrder() {
        Result<String> result = orderFeignClient.message1();
        return result.getData();
    }

    // @Transactional(rollbackFor = Exception.class)
    @Override
    public void reduceInventory(Long pid, Integer number) {
        int i = 1 / 0;
        productMapper.findById(pid).ifPresent(product -> {
            product.setStock(product.getStock() - number);
            productMapper.save(product);
        });
    }
}
