package com.wh.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ShopGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopGatewayApplication.class, args);
    }
}
