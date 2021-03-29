package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 该类用于封装数据返回给前端
 *
 * @param <T> 后端查询的数据信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code; // 结果码 eg. 200, 404
    private String message; // 结果信息
    T data; // 返回给前端的数据
}
