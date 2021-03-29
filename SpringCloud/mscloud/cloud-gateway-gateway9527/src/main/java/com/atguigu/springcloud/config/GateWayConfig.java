package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // 当访问 localhost:9527/guonei的时候会路由到http://news.baidu.com/guonei
        routes.route("path_rote_atguigu", // 配置路由id
                r -> r.path("/guonei") // 配置访问路径
                        .uri("http://news.baidu.com/guonei")) // 配置跳转路径
                .build();
        return routes.build();
    }
}
