package com.wh.user.service;

import com.wh.dao.entity.Order;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
public interface UserService {
    Order createOrder(Long pid);
}
