package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 定义负载均衡接口
 */
public interface LoadBalancer {
    /**
     * 根据传入的服务名，从这些服务的实例中选择一个返回
     *
     * @param serviceName 服务名称
     * @return 服务的某个实例
     */
    ServiceInstance getServiceInstances(String serviceName);
}
