package com.xander.java8._01funcIntf;

import java.util.function.Predicate;

/**
 * Description: 判断一个十六进制字符串是否小于100
 * Predicate<T>断定型接口: 判断 T 类型的参数，是否满足某约束，返回 boolean 值
 *
 * @author Xander
 * datetime: 2020/8/31 9:28
 */
public class Lt100Predicate implements Predicate<String> {

    /**
     * 判断一个十六进制字符串是否小于100
     *
     * @param s
     * @return
     */
    @Override
    public boolean test(String s) {
        Long value = Long.valueOf(s, 16);
        return value.longValue() > 100;
    }
}
