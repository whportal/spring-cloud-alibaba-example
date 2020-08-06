package com.wh.product.mapper;

import com.wh.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
public interface ProductMapper extends JpaRepository<Product, Long> {
}
