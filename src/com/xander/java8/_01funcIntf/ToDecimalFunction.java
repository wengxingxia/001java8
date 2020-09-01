package com.xander.java8._01funcIntf;

import java.util.function.Function;

/**
 * Description: 十六进制字符串转为十进制 function
 * Function<T, R> 函数型接口: 对 T 类型的参数进行操作，返回 R 类型的结果
 *
 * @author Xander
 * datetime: 2020/8/31 9:21
 */
public class ToDecimalFunction implements Function<String, Long> {

    /**
     * 将字符串转为 long 值
     *
     * @param s
     * @return
     */
    @Override
    public Long apply(String s) {
        long value = Long.parseLong(s);
        return value;
    }
}
