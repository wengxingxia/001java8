package com.xander.java8._01funcIntf;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Description: Consumer: 遍历打印 Set
 * Consumer<T>消费型接口：对给定的 T 类型的参数，进行操作
 * @author Xander
 * datetime: 2020/8/31 9:07
 */
public class SetConsumer implements Consumer<Set> {

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
}
