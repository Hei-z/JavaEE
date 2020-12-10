package com.atguigu.test;

import java.util.UUID;

public class ThreadLocalTest {
    // 泛型为数据的类型，可以认为有一个隐含的key，其存放了线程的标识
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                String val = UUID.randomUUID().toString().substring(0, 5);
                System.out.println("current thread name is " + name + " and set value " + val);
                threadLocal.set(val);
                System.out.println("current thread name is " + name + " and get value " + threadLocal.get());
            }).start();
        }
    }
}
