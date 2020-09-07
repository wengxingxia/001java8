package com.xander.java8._05optional;

import com.xander.java8._03methodRef.Person;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/7 9:02
 */
public class OptionalTest {

    @Test
    public void test() {
        Person p = new Person("张三", 18);

        // 通过给定的value，返回一个值为给定value的Optional实例，若value为null，则抛出NullPointerException
        // Optional<Object> o = Optional.of(null);
        Optional<Person> opPerson = Optional.of(p);
        System.out.println("of(T value): " + opPerson);

        // 通过给定的value，返回一个值为给定value的Optional实例，value可为null，若value为null，则返回 Optional.EMPTY
        Optional<Person> opNull = Optional.ofNullable(null);
        System.out.println("ofNullable(T value): " + opNull);

        // 如果此 Optional 存在值，则返回值，否则抛出NoSuchElementException
        // Person person = opNull.get();// 抛出NoSuchElementException
        Person person = opPerson.get();
        System.out.println("T get(): " + person);

        // 如果存在值 value!=null，返回true，否则返回false
        boolean opNullPresent = opNull.isPresent();
        System.out.println("opNull persent: " + opNullPresent);

        if (!opNull.isPresent()) {
            System.out.println("opNull 的 value == null");
        } else {
            System.out.println("opNull 的 value = " + opNull.get());
        }

        boolean opPersonPresent = opPerson.isPresent();
        System.out.println("opPerson persent: " + opPersonPresent);

        // 如果 opPerson 的 value != null，输出下面语句
        opPerson.ifPresent(value -> System.out.println(value.getName() + "的年龄是" + value.getAge()));

        // 如果 Optional 的值存在而且匹配给定的 predicate（断定型函数式接口），返回当前Optional，否则返回一个空Optional(值为null)
        System.out.println("opNull.filter(): " + opNull.filter(value -> value.getAge() == 18));
        System.out.println("opPerson.filter() 匹配age==18: " + opPerson.filter(value -> value.getAge() == 18));
        System.out.println("opPerson.filter() 匹配age==19: " + opPerson.filter(value -> value.getAge() == 19));

        // 如果值存在，对该值应用给定的映射方法，如果是非null的结果，返回一个描述该值的 optional，否则返回一个空Optional(值为null)
        // Person::getName 方法引用等同于 lambda表达式：value->value.getName()
        // Function<Person, String> funcGetName = value->value.getName();
        Function<Person, String> funcGetName = Person::getName;
        Optional<String> opNameofOpNull = opNull.map(funcGetName);
        Optional<String> opNameofOpPerson = opPerson.map(funcGetName);
        System.out.println("opNull.map(): " + opNameofOpNull);
        System.out.println("opPerson.map(): " + opNameofOpPerson);

        // 如果 Optional 的值存在,应用提供的映射方法并返回结果 Optional，否则返回一个空的Optional
        Optional<Person> op1 = opNull.flatMap(value -> Optional.of(new Person("李四", 20)));
        Optional<Person> op2 = opPerson.flatMap(value -> Optional.of(new Person("李四", 20)));
        System.out.println("opNull.flatMap(): " + op1);
        System.out.println("opPerson.flatMap(): " + op2);

        // 如果此Optional的value不为null,则返回value,否则返回给定的other对象
        Person wangwu = new Person("王五", 25);
        System.out.println("opNull.orElse(): " + opNull.orElse(wangwu));
        System.out.println("opPerson.orElse(): " + opPerson.orElse(wangwu));

        // 如果此Optional的value不为null,则返回value,否则通过调用给定的Supplier（供给型函数式接口）返回一个T类型的对象
        Supplier<Person> supplier = () -> new Person("赵六", 26);
        System.out.println("opNull.orElseGet(): " + opNull.orElseGet(supplier));
        System.out.println("opPerson.orElseGet(): " + opPerson.orElseGet(supplier));

        // 如果此Optional的value不为null,则返回value,否则通过调用给定的Supplier创建一个Throwable
        Supplier<NullPointerException> nullPointerExceptionSupplier = () -> new NullPointerException("value is null");
        System.out.println("opPerson.orElseThrow(): " + opPerson.orElseThrow(nullPointerExceptionSupplier));
        System.out.println("opNull.orElseThrow(): " + opNull.orElseThrow(nullPointerExceptionSupplier));
    }
}

