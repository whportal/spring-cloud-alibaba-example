package com.wh.product.controller;

import com.wh.common.response.Result;
import com.wh.dao.entity.Product;
import com.wh.dto.ProductDTO;
import com.wh.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-27
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Result<Product> findById(@PathVariable("id") Long id) {
        Product product = productService.findByPid(id);
        log.info("查询商品：[product:{},timestamp:{}]", product, System.nanoTime());
        return Result.success(product);
    }

    @GetMapping("/order/message")
    public Result<String> getMessageFromOrder() {
        return Result.success(productService.getMessageFromOrder());
    }

    @PutMapping("/reduce")
    public Result<Void> reduceInventory(@RequestBody ProductDTO productDTO) {
        productService.reduceInventory(productDTO.getPid(),productDTO.getNumber());
        return Result.success();
    }
}
