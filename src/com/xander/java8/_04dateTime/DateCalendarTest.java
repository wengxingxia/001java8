package com.xander.java8._04dateTime;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Description: Date 和 Calendar 的简单使用
 *
 * @author Xander
 * datetime: 2020/9/3 8:00
 */
public class DateCalendarTest {

    /**
     * 测试 java.util.Date
     */
    @Test
    public void testDateAPI() {
        // 1、Date()  Date 空参构造器，调用的构造器 Date(long date)，入参是 System.currentTimeMillis()
        Date date1 = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 用时间戳入参的构造器
        // 2、Date(long date) 入参 date 表示自1970年1月1日00:00:00 GMT(格林尼治标准时间)以来的毫秒数
        Date date2 = new Date(0);
        System.out.println("空参构造器：" + dateFormat.format(date1));
        System.out.println("时间戳入参构造器：" + dateFormat.format(date2));

        // 3、getTime()  // 返回 1970年1月1日00:00:00 GMT 到当前 Date 的毫秒数
        long time = date1.getTime();
        System.out.println("date1的毫秒数：" + time);

        //  setTime(long time) 入参 time 表示自1970年1月1日00:00:00 GMT(格林尼治标准时间)以来的毫秒数
        date2.setTime(System.currentTimeMillis());
        System.out.println("date2设置时间后的毫秒数：" + date2.getTime());

        // before(Date when) 测试此日期是否早于入参指定的日期。
        boolean before = date1.before(date2);
        System.out.println("data1 before date2 ? " + before);

        // after(Date when) 测试此日期是否晚于入参指定的日期。
        boolean after = date1.after(date2);
        System.out.println("data1 after date2 ? " + after);

        Instant instant = date1.toInstant();
        System.out.println("date1.toInstant(): " + instant);

        Date from = Date.from(instant);
        System.out.println("Date.from(instant): " + from.getTime());
    }

    /**
     * 测试 java.util.Calendar
     */
    @Test
    public void testCalendarAPI() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println("当前日历时间：" + simpleDateFormat.format(cal.getTime()));
        System.out.println("当前日历时间的毫秒数：" + cal.getTimeInMillis());
        //年
        System.out.println("年:" + cal.get(Calendar.YEAR));
        //月：month从0开始
        System.out.println("月(month从0开始):" + cal.get(Calendar.MONTH));
        //日
        System.out.println("日:" + cal.get(Calendar.DATE));
        //设置年、月、日、时、分、秒
        cal.set(2020, 10, 1, 8, 0, 0);
        System.out.println("设置后的时间：" + simpleDateFormat.format(cal.getTime()));
        //年份+1
        cal.add(Calendar.YEAR, 1);
        System.out.println("将年份+1：" + simpleDateFormat.format(cal.getTime()));
    }

    /**
     * 测试 SimpleDateFormat
     */
    @Test
    public void testSimpleDateFormatAPI() throws ParseException {
        //定义时间字符串的pattern
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        //把字符串解析为Date
        Date date = format.parse("2020-09-03 12:30:29");
        System.out.println("String to Date：" + date);

        //打印 Date 的时间字符串
        Date now = new Date();
        System.out.println("Date to String：" + format.format(now));
    }


    /**
     * 演示SimpleDateFormat类线程不安全
     */
    @Test
    public void testSimpleDateFormatUnSafe() throws InterruptedException {
        // 闭锁：主线程等待所有子线程运行完后，再继续往下运行
        // 不了解 CountDownLatch 可以忽略 CountDownLatch 的相关代码，后面会有系列文章介绍
        CountDownLatch latch = new CountDownLatch(5);

        //定义时间字符串的pattern
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        for (int i = 1; i <= 5; i++) {
            int millis = i * 1000;
            new Thread(() -> {
                Date date = new Date(millis);
                String strDateTime = format.format(date);
                System.out.println("当前线程" + Thread.currentThread().getName() + " 对应的时间字符串：" + strDateTime);
                // 不了解 CountDownLatch 可以忽略 CountDownLatch 的相关代码，后面会有系列文章介绍
                latch.countDown();//闭锁数值减一
            }).start();
        }
        // 主线程阻塞，等待所有子线程运行完后，再继续往下运行
        // 不了解 CountDownLatch 可以忽略 CountDownLatch 的相关代码，后面会有系列文章介绍
        latch.await();
    }


    /**
     * 演示 DateTimeFormatter 类线程安全
     */
    @Test
    public void testDateTimeFormatterSafe() throws InterruptedException {
        // 闭锁：主线程等待所有子线程运行完后，再继续往下运行
        // 不了解 CountDownLatch 可以忽略 CountDownLatch 的相关代码，后面会有系列文章介绍
        CountDownLatch latch = new CountDownLatch(5);

        //定义时间字符串的pattern
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        for (int i = 1; i <= 5; i++) {
            int seconds = i;
            new Thread(() -> {
                LocalDateTime of = LocalDateTime.of(2020, 9, 3, 12, 14, 30);
                of = of.plusSeconds(seconds);//每个线程分别增加传进来的秒数
                String strDateTime = formatter.format(of);
                System.out.println("当前线程" + Thread.currentThread().getName() + " 对应的时间字符串：" + strDateTime);
                // 不了解 CountDownLatch 可以忽略 CountDownLatch 的相关代码，后面会有系列文章介绍
                latch.countDown();//闭锁数值减一
            }).start();
        }
        // 主线程阻塞，等待所有子线程运行完后，再继续往下运行
        // 不了解 CountDownLatch 可以忽略 CountDownLatch 的相关代码，后面会有系列文章介绍
        latch.await();
    }

}
