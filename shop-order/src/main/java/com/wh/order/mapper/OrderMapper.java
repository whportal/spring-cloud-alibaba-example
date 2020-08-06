package com.wh.order.mapper;

import com.wh.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
public interface OrderMapper extends JpaRepository<Order, Long> {
}
