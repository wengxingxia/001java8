package com.xander.java8._06default_static_method;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/7 10:20
 */
public interface DefaultIntf {

    /**
     * default 方法
     */
    default void sayHello() {
        System.out.println("DefaultIntf say hello 2 you");
    }
}