package com.wh.user.feign;

import com.wh.common.response.Result;
import com.wh.dao.entity.Product;
import com.wh.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @GetMapping("/product/{id}")
    Result<Product> findById(@PathVariable("id") Long id);

    @PutMapping("product/reduce")
    Result<Void> reduceInventory(@RequestBody ProductDTO productDTO);
}
