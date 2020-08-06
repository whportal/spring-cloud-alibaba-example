package com.wh.order.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>定义 Sentinel 来源处理规则 配合 授权规则#流控应用 使用</p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-28
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {

        // 假设能够获取到请求来源
        return httpServletRequest.getParameter("origin");
    }
}
