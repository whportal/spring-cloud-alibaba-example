package com.wh.order.controller;

import com.wh.common.response.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     RefreshScope 注解实现配置文件动态刷新
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020/08/03
 */
@RestController
@RequestMapping("nacos/config")
@RefreshScope
public class NacosConfigController {

    @Value("${wh-portal.name}")
    private String name;

    @GetMapping("name")
    public Result<String> getNacosConfig() {

        return Result.success(name);
    }
}
