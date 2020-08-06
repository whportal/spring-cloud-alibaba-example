package com.wh.product.service;

import com.wh.dao.entity.Product;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
public interface ProductService {

    Product findByPid(Long pid);

    String getMessageFromOrder();

    void reduceInventory(Long pid, Integer number);
}
