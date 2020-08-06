package com.wh.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p>
 *     自定义路由转发规则，继承 AbstractRoutePredicateFactory 类，泛型为 配置类，配置类用于接收配置文件中的配置
 *
 *     从配置文件获取要 token 的 key，判断请求头里面是否包含指定的 tokeKey 并且 不能为空
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    /**
     * Method key.
     */
    public static final String TOKEN_KEY = "token";

    public TokenRoutePredicateFactory() {
        super(TokenRoutePredicateFactory.Config.class);
    }

    /**
     *  用于从配置文件中获取参数值赋值到配置类的属性上
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        // 这里的顺序要跟配置文件中的参数顺序一致
        return Arrays.asList(TOKEN_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(TokenRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                // 从 ServerWebExchange 获取传入的参数
                List<String> token = exchange.getRequest().getHeaders().get(config.getToken());
                return !CollectionUtils.isEmpty(token) && !StringUtils.isEmpty(token.get(0));
            }
        };
    }

    /**
     * 自定义一个配置类用于接收配置文件中的参数
     */
    public static class Config {

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
