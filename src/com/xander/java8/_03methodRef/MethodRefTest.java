package com.xander.java8._03methodRef;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * Description: 方法引用简单应用
 *
 * @author Xander
 * datetime: 2020/8/31 23:57
 */
public class MethodRefTest {

    @Test
    public void test00() {

        //lambda表达式
        Consumer<String> cLambda = str -> System.out.println(str);
        // 对象::实例方法名
        Consumer<String> cMethRef = System.out::println;
        cLambda.accept("hello Method Reference");
        cMethRef.accept("hello Method Reference");

    }

    @Test
    public void test01() {
        Calc calc = new Calc();
        //lambda表达式
        BinaryOperator<Integer> oprLambda = (x, y) -> x + y;
        // 对象::实例方法名
        BinaryOperator<Integer> oprMethRef = calc::add;
        System.out.println(oprLambda.apply(2, 3));
        System.out.println(oprMethRef.apply(2, 3));
    }

    @Test
    public void test02() {
        //lambda表达式
        BiConsumer<Person, Person> bcLambda = (x, y) -> System.out.println(x.getName() + " say hello to " + y.getName());
        // 类名::实例方法名
        BiConsumer<Person, Person> bcMethRef = Person::sayHello;
        bcLambda.accept(new Person("P1", 18), new Person("P2", 18));
        bcMethRef.accept(new Person("P3", 18), new Person("P4", 18));
    }

    @Test
    public void test03() {
        // 判断两个String是否equals
        //lambda表达式
        BiFunction<String, String, Boolean> bfLambda = (x, y) -> x.equals(y);
        // 类名::实例方法名
        BiFunction<String, String, Boolean> bfMethRef = String::equals;
        System.out.println(bfLambda.apply("a", "b"));
        System.out.println(bfMethRef.apply("a", "a"));

        //求 String 的 hashCode
        //lambda表达式
        Function<String, Integer> funcLambda = str -> str.hashCode();
        // 类名::实例方法名
        Function<String, Integer> funcMethRef = String::hashCode;
        System.out.println(funcLambda.apply("a"));
        System.out.println(funcMethRef.apply("a"));
    }

    @Test
    public void test04() {
        //lambda表达式
        BinaryOperator<Long> bfLambda = (x, y) -> x * y;
        // 类名::静态方法名
        BinaryOperator<Long> bfMethRef = Calc::multi;
        System.out.println(bfLambda.apply(2L, 3L));
        System.out.println(bfMethRef.apply(2L, 3L));
    }

    @Test
    public void test05() {
        //lambda表达式
        BinaryOperator<Double> bfLambda = (x, y) -> Math.pow(x, y);
        // 类名::静态方法名
        BinaryOperator<Double> bfMethRef = Math::pow;
        System.out.println(bfLambda.apply(2D, 3D));
        System.out.println(bfMethRef.apply(2D, 3D));
    }

    @Test
    public void test06() {
        //通过函数式接口IntFunction 创建 Integer 实例
        //lambda表达式
        IntFunction<Integer> intFuncLambda = i -> new Integer(i);
        // 构造器引用：类名::new
        IntFunction<Integer> intFuncConstRef = Integer::new;
        //lambda表达式生成 函数式接口IntFunction 的实例，执行apply方法后，生成的 Integer 实例
        Integer integerLambda = intFuncLambda.apply(5);
        //构造器引用生成 函数式接口IntFunction 的实例，执行apply方法后，生成的 Integer 实例
        Integer integerConstRef = intFuncConstRef.apply(15);
        System.out.println(integerLambda.intValue());
        System.out.println(integerConstRef.intValue());
    }

    @Test
    public void test07() {
        //通过函数式接口BiFunction 创建 Person 实例
        //lambda表达式
        BiFunction<String, Long, Person> bfLambda = (name, age) -> new Person(name, age);
        // 构造器引用：类名::new
        BiFunction<String, Long, Person> bfConstRef = Person::new;
        // lambda表达式生成的Person
        Person personLambda = bfLambda.apply("张三", 20L);
        // 构造器引用生成的Person
        Person personConstRef = bfConstRef.apply("李四", 25L);
        System.out.println(personLambda);
        System.out.println(personConstRef);
    }

    @Test
    public void test08() {
        // 通过数组引用，生成 String[] 实例
        //lambda表达式
        Function<Integer, String[]> funcLambda = length -> new String[length];
        // 数组引用：类名[]::new
        Function<Integer, String[]> funcArrayRef = String[]::new;
        // lambda表达式生成的String[]
        String[] arrayLambda = funcLambda.apply(2);
        arrayLambda[0] = "lambda";
        // 数组引用生成的String[]
        String[] arrayArrayRef = funcArrayRef.apply(2);
        arrayArrayRef[0] = "Array Reference";
        System.out.println("lambda生成数组：" + Arrays.toString(arrayLambda));
        System.out.println("数组引用生成数组：" + Arrays.toString(arrayArrayRef));
    }
}