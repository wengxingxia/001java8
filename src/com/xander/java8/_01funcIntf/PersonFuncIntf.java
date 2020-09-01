package com.xander.java8._01funcIntf;

/**
 * Description: 自定义的函数式接口
 *
 * @author Xander
 * datetime: 2020/8/30 8:43
 */
@FunctionalInterface
public interface PersonFuncIntf {

    /**
     * 说话
     *
     * @param word 要说的话
     */
    void say(String word);
}
