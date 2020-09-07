package com.xander.java8._06default_static_method;

import org.junit.Test;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/7 10:08
 */
public class DefaultMethodTest {

    @Test
    public void testDefaultMethod() {
        DefaultIntf dog = new Dog();
        DefaultIntf cat = new Cat();
        Bird bird = new Bird();
        Fish fish = new Fish();

        // 执行默认方法
        // 实现类中没有重写default方法，执行的是接口中的default
        dog.sayHello();
        // 实现类中对default进行重写，则执行的是重写后的方法
        cat.sayHello();
        // 如果类实现了一个接口和继承了一个父类，接口的默认方法和父类中某一个方法具有相同名称和参数列表，而且子类没有进行该方法的重写，则使用的是父类的方法。
        bird.sayHello();
        // **方法冲突**，类中实现了两个接口，这两个接口中具有相同名称和参数列表的方法(不管是不是default方法)，如类中没有对default方法进行重写，则编译出错。子类中必须必须覆盖该方法来解决冲突。
        fish.sayHello();
    }

}


