package com.xander.java8._01funcIntf;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Description: 随机十六进制字符串生成器 Supplier
 * Supplier 供给型接口: 获取一个 T 类型的对象
 *
 * @author Xander
 * datetime: 2020/8/31 9:16
 */
public class RandomHexStrSupplier implements Supplier<String> {

    /**
     * 要生成的字符串长度
     */
    public static final int LENGTH = 2;// 字符串最大长度

    /**
     * 十六进制字符数组
     */
    public char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @Override
    public String get() {
        String hexStr = this.genHexStr(LENGTH);
        return hexStr;
    }


    /**
     * 生成随机 16 进制字符串
     *
     * @param length
     * @return
     */
    private String genHexStr(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(this.hexChars[random.nextInt(this.hexChars.length)]);
        }
        return sb.toString();
    }
}
