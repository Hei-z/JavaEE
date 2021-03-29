package com.atguigu.springcloud.lb.impl;

import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义轮询负载均衡器
 */
@Component
public class MyLoadBalancer implements LoadBalancer {
    @Autowired
    private DiscoveryClient discoveryClient;

    // 统计服务访问次数
    private final AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    /**
     * @return 根据访问次数返回值，比如第一次访问返回1，第二次访问返回2，....
     */
    private int incrementAndGetModulo() {
        int cur; // 当前计数
        int nxt; // 下一个计数
        do {
            cur = nextServerCyclicCounter.get(); // 首先我们获取内存中nextServerCyclicCounter的值
            nxt = cur == Integer.MAX_VALUE ? 0 : cur + 1; // 将cur + 1
            // 判断在cur + 1的过程中有没有人修改了nextServerCyclicCounter在内存中的值
            // cur表示我们从内存中读取时候的值，该方法会将cur的值和内存中的值进行比较，如果不相等，那么返回false
            // 通过这一步目的就是为了保证我们在修改cur的时候，没有其他线程修改过内存中的值
        } while (!nextServerCyclicCounter.compareAndSet(cur, nxt));
        return nxt;
    }

    @Override
    public ServiceInstance getServiceInstances(String serviceName) {
        // 获取服务所有的实例
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (instances == null || instances.isEmpty()) {
            return null;
        }
        // 获取到下一个应该访问的服务实例
        int idx = incrementAndGetModulo() % instances.size();
        return instances.get(idx);
    }
}
