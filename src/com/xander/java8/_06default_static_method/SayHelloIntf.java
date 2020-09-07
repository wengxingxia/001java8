package com.xander.java8._06default_static_method;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/7 11:02
 */
public interface SayHelloIntf {
    /**
     * default 方法
     */
    default void sayHello() {
        System.out.println("SayHelloIntf hello");
    }
}
