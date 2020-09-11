package com.xander.java8._06default_static_method;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description: 接口：包含一个default方法，该接口所有的实例都可以通过该default获取泛型类型
 *
 * @author Xander
 * datetime: 2020/9/7 17:55
 */
public interface GenericIntf<T> {

    /**
     * 默认方法：泛型T的 Class<T>
     *
     * @return
     */
    default Class<T> getGenericClass() {
        Type[] types = this.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Class<T> tClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        return tClass;
    }
}
