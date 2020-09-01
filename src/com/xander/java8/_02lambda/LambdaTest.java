package com.xander.java8._02lambda;


import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Description: Lambda 表达式的简单使用
 *
 * @author Xander
 * datetime: 2020/8/31 10:37
 */
public class LambdaTest {

    /**
     * 分别通过匿名内部类和 lambda 表达式方式新建 Runnable 对象，创建线程，打印随机整数
     */
    @Test
    public void test00() {
        Random random = new Random();
        // 匿名内部类创建一个 Runable 对象
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类：" + random.nextInt(10));
            }
        });
        thread.start();
        // lambda 表达式创建一个 Runable(函数式接口) 对象
        Runnable rLambda = () -> System.out.println("lambda表达式1：" + random.nextInt(10));
        Thread tLambda1 = new Thread(rLambda);
        tLambda1.start();

        //lambda 可以代替函数式接口的匿名内部类作为参数传递
        Thread tLambda2 = new Thread(() -> System.out.println("lambda表达式2：" + random.nextInt(10)));
        tLambda2.start();
    }


    /**
     * 参数：无参
     */
    @Test
    public void test01() {
        // 参数：无参，参数部分为 ()
        Supplier<Integer> supplier = () -> {
            int num = new Random().nextInt(10);
            return num;
        };
        System.out.println(supplier.get());
    }

    /**
     * 参数：只有一个参数
     */
    @Test
    public void test02() {
        // 参数：只有一个参数，可以省略括号 ()
        Consumer<String> c1 = str -> {
            System.out.println(str);
        };
        c1.accept("hello lambda");
    }

    /**
     * 参数：多个参数
     */
    @Test
    public void test03() {
        //参数：多个参数，所有的参数写在小括号 () 中
        BinaryOperator<Integer> binaryOperator = (n1, n2) -> {
            return n1 + n2;
        };
        System.out.println(binaryOperator.apply(2, 3));
    }

    /**
     * lambda体：一行语句
     */
    @Test
    public void test04() {
        // lambda体：一行语句，返回值，可以省略大括号
        Consumer<String> c1 = str -> System.out.println(str);
        c1.accept("lambda体：一行语句，返回值，可以省略大括号");
    }

    /**
     * lambda体：一行语句，有返回值
     */
    @Test
    public void test05() {
        // 二元运算：加法
        BinaryOperator<Integer> biOpr1 = (n1, n2) -> {
            return n1 + n2;
        };
        // 二元运算：乘法
        // lambda体：一行语句，有返回值，可以省略 return 关键字 和 大括号 {}
        BinaryOperator<Integer> biOpr2 = (n1, n2) -> n1 * n2;

        System.out.println(biOpr1.apply(5, 10));
        System.out.println(biOpr2.apply(5, 10));
    }

    /**
     * lambda体：多行语句
     */
    @Test
    public void test06() throws UnsupportedEncodingException {

        // 二元运算：加法
        // lambda体：多行语句，所有代码语句要写在大括号 {} 中
        BinaryOperator<Integer> biOpr1 = (n1, n2) -> {
            System.out.println("参数一：" + n1);
            System.out.println("参数二：" + n2);
            return n1 + n2;
        };
        System.out.println("加法运算结果：" + biOpr1.apply(5, 10));

        String encode = URLEncoder.encode("01-函数式接口和lambda表达式.md", "utf-8");
        System.out.println(encode);

    }


}
