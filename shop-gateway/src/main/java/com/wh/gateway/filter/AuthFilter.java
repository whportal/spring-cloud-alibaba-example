package com.wh.gateway.filter;

import com.google.common.base.Stopwatch;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 在Gateway中, Filter的生命周期只有两个：“pre” 和 “post”。
 * - PRE： 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
 * - POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
 * <p>
 * 自定义全局过滤器
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.err.println("Pre ---------------------->" + System.nanoTime());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            stopWatch.stop();
            long totalTimeMillis = stopWatch.getTotalTimeMillis();
            System.err.println("请求消耗时间： ---------------------->" + totalTimeMillis);
            System.err.println("Post ---------------------->" + System.nanoTime());
        }));
    }

    /**
     * 过滤器的优先级 值越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
