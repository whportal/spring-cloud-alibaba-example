package com.wh.order.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import com.wh.common.response.Result;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Sentinel 自定义异常 </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-28
 */
@Component
public class SentinelExceptionHandler implements BlockExceptionHandler {

    // BlockException 包含 Sentinel 的5个异常
    // FlowException 限流异常
    // DegradeException 降级异常
    // ParamFlowException 参数限流异常
    // AuthorityException 授权异常
    // SystemBlockException 系统负载异常

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {

        String message = "";
        if (e instanceof FlowException) {
            message = "接口被限流了...";
        } else if (e instanceof DegradeException) {
            message = "接口被降级了...";
        } else if (e instanceof ParamFlowException) {
            message = "接口被参数限流了...";
        } else if (e instanceof AuthorityException) {
            message = "接口授权异常...";
        } else if (e instanceof SystemBlockException) {
            message = "系统负载异常...";
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONObject.toJSONString(Result.failed(message)));
    }
}
