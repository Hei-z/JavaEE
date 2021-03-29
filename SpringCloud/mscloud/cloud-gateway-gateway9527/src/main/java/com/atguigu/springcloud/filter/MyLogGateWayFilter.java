package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    // Mono是响应式编程的内容，类似于ModelAndView
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // getFirst表示返回uname这个key对应的第一个value值
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname != null && uname.equals("zs")) {
            // 前置逻辑
            log.info("come in.....");
            // 放行
            // then表示后置逻辑
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 后置处理
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("leave....");
            }));
        }
        // 表示响应完成，即不放行
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
