package com.xander.java8._06default_static_method;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/7 11:20
 */
public interface StaticIntf {

    static void printInfo() {
        System.out.println("执行接口中的static方法：" + StaticIntf.class);
    }
}
