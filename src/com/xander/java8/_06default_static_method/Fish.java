package com.xander.java8._06default_static_method;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/7 11:09
 */
public class Fish implements SayHelloIntf, DefaultIntf {

    /**
     * **方法冲突**，类中实现了两个接口，这两个接口中具有相同名称和参数列表的方法(不管是不是default方法)，
     * 如类中没有对default方法进行重写，则编译出错。子类中必须必须覆盖该方法来解决冲突。
     */
    @Override
    public void sayHello() {
        System.out.println("Hello this is Fish");
    }
}
