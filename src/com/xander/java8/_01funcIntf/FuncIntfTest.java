package com.xander.java8._01funcIntf;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Description: 函数式接口简单使用
 *
 * @author Xander
 * datetime: 2020/8/31 9:25
 */
public class FuncIntfTest {

    /**
     * 要生成的字符串长度
     */
    public static final int LENGTH = 2;// 字符串最大长度

    /**
     * 十六进制字符数组
     */
    public static char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static void main(String[] args) {
        int setLength = 5;// set 的 length
        Set<String> set = new LinkedHashSet<>();

        Supplier<String> supplier = getRandomHexStrSupplier();// 获取一个 Supplier：随机十六进制字符串生成器
        for (int i = 0; i < setLength; i++) {
            set.add(supplier.get());//生成随机 16 进制字符串
        }
        Consumer<Set> setConsumer = getSetConsumer();//获取一个Consumer: 遍历打印 Set
        System.out.println("----------打印所有的16进制字符串---------");
        setConsumer.accept(set);//遍历打印 set 中的每个元素

        //将字符串转为 long 值
        Function<String, Long> toDecimalFunction = new Function<String, Long>() {
            @Override
            public Long apply(String s) {
                Long value = Long.valueOf(s, 16);//将16进制字符串转为10进制的long值
                return value;
            }
        };

        Predicate<String> lt150Predicate = new Predicate<String>() {
            /**
             * 判断一个十六进制字符串是否小于150
             *
             * @param s
             * @return
             */
            @Override
            public boolean test(String s) {
                Long value = Long.valueOf(s, 16);
                return value.longValue() < 150;
            }
        };

        Set<Long> longSet = new LinkedHashSet<>();
        for (String str : set) {
            if (lt150Predicate.test(str)) {//判断一个十六进制字符串是否小于150
                Long value = toDecimalFunction.apply(str);//将16进制字符串转为10进制的long值
                longSet.add(value);
            }
        }
        System.out.println("----------打印小于150的值---------");
        setConsumer.accept(longSet);//遍历打印 longSet 中的每个元素
    }

    /**
     * 获取一个Supplier：随机十六进制字符串生成器
     *
     * @return
     */
    private static Supplier<String> getRandomHexStrSupplier() {
        Supplier<String> supplier = new Supplier<String>() {

            /**
             * 生成随机 16 进制字符串
             *
             * @return
             */
            @Override
            public String get() {
                Random random = new Random();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < LENGTH; i++) {
                    sb.append(hexChars[random.nextInt(hexChars.length)]);
                }
                return sb.toString();
            }
        };
        return supplier;
    }

    /**
     * 获取一个Consumer: 遍历打印 Set
     *
     * @return
     */
    private static Consumer<Set> getSetConsumer() {
        Consumer<Set> consumer = new Consumer<Set>() {

            /**
             * 遍历打印 set 中的每个元素
             *
             * @param set
             */
            @Override
            public void accept(Set set) {
                if (set == null || set.isEmpty())
                    return;
                for (Object s : set) {
                    System.out.println(s);
                }
            }
        };
        return consumer;
    }
}
