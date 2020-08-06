package com.wh.gateway.filter.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 自定义局部过滤器
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-31
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public static final String CONSOLE_LOG = "consoleLog";
    public static final String FILE_LOG = "fileLog";

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    /**
     * 读取配置文件中的参数 赋值到配置类中
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(CONSOLE_LOG, FILE_LOG);
    }

    @Override
    public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange,
                                     GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();

                // 此处仅模拟记录日志的动作
                if (config.isConsoleLog()) {
                    System.err.println("ConsoleLog is on...");
                }
                if (config.isFileLog()) {
                    System.err.println("FileLog is on...");
                }
                return chain.filter(exchange);
            }
        };
    }

    public static class Config {

        private boolean consoleLog;
        private boolean fileLog;

        public boolean isConsoleLog() {
            return consoleLog;
        }

        public void setConsoleLog(boolean consoleLog) {
            this.consoleLog = consoleLog;
        }

        public boolean isFileLog() {
            return fileLog;
        }

        public void setFileLog(boolean fileLog) {
            this.fileLog = fileLog;
        }
    }
}
