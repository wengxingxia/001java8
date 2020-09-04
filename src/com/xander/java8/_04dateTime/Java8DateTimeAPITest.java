package com.xander.java8._04dateTime;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author Xander
 * datetime: 2020/9/3 14:37
 */
public class Java8DateTimeAPITest {


    /**
     * 测试 LocalDate
     */
    @Test
    public void testLocalDate() {
        // 从给定的年、月和日获取LocalDate的实例。
        LocalDate ldOf = LocalDate.of(2020, 9, 3);
        System.out.println("从给定的年、月和日获取LocalDate的实例: " + ldOf);
        // 从默认时区的系统时钟获取当前日期
        LocalDate ldNow = LocalDate.now();
        System.out.println("从默认时区的系统时钟获取当前日期: " + ldNow);
        // 返回一个 LocalDateTime ，日期为当前日期，时间为指定的小时、分钟和秒
        LocalDateTime localDateTime = ldNow.atTime(1, 2, 30);
        System.out.println("返回一个 LocalDateTime日期为当前日期，时间为指定的小时、分钟和秒：" + localDateTime);

        // 返回月份中第几天
        System.out.println("返回月份中第几天: " + ldNow.getDayOfMonth());
        // 返回 DayOfWeek 枚举，表示周几
        System.out.println("返回 DayOfWeek 枚举，表示周几: " + ldNow.getDayOfWeek());
        // 返回年份中第几天
        System.out.println("返回年份中第几天: " + ldNow.getDayOfYear());

        // 返回 Month 枚举，表示月份
        System.out.println("返回 Month 枚举，表示月份: " + ldNow.getMonth());
        // 返回月份对应的数值 1-12
        System.out.println("返回月份对应的数值 1-12 : " + ldNow.getMonthValue());

        // 当前日期减去多少天，返回新的LocalDate实例
        System.out.println("当前日期减去5天，返回新的LocalDate实例: " + ldNow.minusDays(5));

        // 当前日期减去多少月，返回新的LocalDate实例
        System.out.println("当前日期减去1月，返回新的LocalDate实例: " + ldNow.minusMonths(1));

        // 当前日期加上多少周，返回新的LocalDate实例
        System.out.println("当前日期加上1周，返回新的LocalDate实例: " + ldNow.plusWeeks(1));
        // 当前日期加上多少年，返回新的LocalDate实例
        System.out.println("当前日期加上2年，返回新的LocalDate实例: " + ldNow.plusYears(2));

        // 如果年份是闰年，则为true，否则为false
        System.out.println("今年是否是闰年？" + ldNow.isLeapYear());

        // 如果早于给定的日期，则为true，否则为false
        System.out.println("当前日期是否早于2020-9-3？ " + ldNow.isBefore(ldOf));
        // 如果晚于给定的日期，则为true，否则为false
        System.out.println("当前日期是否晚于2020-9-3？ " + ldNow.isAfter(ldOf));
    }


    /**
     * 测试 LocalTime
     */
    @Test
    public void testLocalTime() {
        // 从给定的hour, minute 和 second获取LocalTime的实例。
        LocalTime ltOf = LocalTime.of(1, 5, 23);
        System.out.println("从给定的hour, minute 和 second获取LocalTime的实例: " + ltOf);
        // 从默认时区的系统时钟获取当前时间
        LocalTime ltNow = LocalTime.now();
        System.out.println("从默认时区的系统时钟获取当前时间: " + ltNow);
        // 这将返回一个 LocalDateTime ，日期为给定的LocalDate，时间为指定的当前时间
        LocalDateTime localDateTime = ltNow.atDate(LocalDate.now());
        System.out.println("这将返回一个 LocalDateTime ，日期为今天(2020-09-04)，时间为指定的当前时间：" + localDateTime);

        // 返回小时
        System.out.println("返回小时: " + ltNow.getHour());
        // 返回分钟
        System.out.println("返回分钟: " + ltNow.getMinute());
        // 返回秒
        System.out.println("返回秒: " + ltNow.getSecond());

        // 返回纳秒
        System.out.println("返回纳秒: " + ltNow.getNano());

        // 当前时间减去多少小时，返回新的LocalTime实例
        System.out.println("当前时间减去1小时，返回新的LocalTime实例: " + ltNow.minusHours(1));

        // 当前时间减去多少分钟，返回新的LocalTime实例
        System.out.println("当前时间减去20分钟，返回新的LocalTime实例: " + ltNow.minusMinutes(20));

        // 当前时间减去多少秒，返回新的LocalTime实例
        System.out.println("当前时间加上10秒，返回新的LocalTime实例: " + ltNow.plusSeconds(10));
        // 当前时间减去多少纳秒，返回新的LocalTime实例
        System.out.println("当前时间加上999纳秒，返回新的LocalTime实例: " + ltNow.plusNanos(999));

        // 调整小时，返回新的LocalTime实例
        System.out.println("调整小时到3时，返回新的LocalTime实例：" + ltNow.withHour(3));
    }

    /**
     * 测试 LocalDateTime
     */
    @Test
    public void testLocalDateTime() {
        // 从给定的year, month, day, hour, minute 和 second 获取LocalDateTime的实例。
        LocalDateTime ldtOf = LocalDateTime.of(2020, 9, 3, 1, 5, 23);
        System.out.println("从给定的year, month, day, hour, minute 和 second 获取LocalDateTime的实例: " + ldtOf);
        // 从默认时区的系统时钟获取当前日期时间 LocalDateTime
        LocalDateTime ldtNow = LocalDateTime.now();
        System.out.println("从默认时区的系统时钟获取当前日期时间 LocalDateTime: " + ldtNow);
        // 这将返回一个带时区的日期时间实例 ZonedDateTime，入参是系统默认时区
        ZonedDateTime zonedDateTime = ldtNow.atZone(ZoneId.systemDefault());
        System.out.println("返回一个带时区的日期时间实例 ZonedDateTime，入参是系统默认时区：" + zonedDateTime);
        // 通过给定 Asia/Shanghai 时区，返回 ZonedDateTime 实例
        ZonedDateTime shZonedDateTime = ldtNow.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("通过给定 Asia/Shanghai 时区，返回 ZonedDateTime 实例：" + shZonedDateTime);

        //返回周几
        long dayOfWeek = ldtNow.getLong(ChronoField.DAY_OF_WEEK);
        System.out.println("返回周几：" + dayOfWeek);

        //日期在天中的毫秒数
        long milliOfDay = ldtNow.getLong(ChronoField.MILLI_OF_DAY);
        System.out.println("日期在天中的毫秒数：" + milliOfDay);
        LocalDateTime endExclusive = ldtNow.withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("日期在天中的毫秒数 Duration：" + Duration.between(endExclusive, ldtNow).toMillis());

        //当前日期是年份中的第几天
        long dayOfYear = ldtNow.getLong(ChronoField.DAY_OF_YEAR);
        System.out.println("getLong(TemporalField field)方式获取当前日期是年份中的第几天：" + dayOfYear);


        // 返回月份中第几天
        System.out.println("返回月份中第几天: " + ldtNow.getDayOfMonth());
        // 返回 DayOfWeek 枚举，表示周几
        System.out.println("返回 DayOfWeek 枚举，表示周几: " + ldtNow.getDayOfWeek());
        // 返回年份中第几天
        System.out.println("返回年份中第几天: " + ldtNow.getDayOfYear());

        // 返回 Month 枚举，表示月份
        System.out.println("返回 Month 枚举，表示月份: " + ldtNow.getMonth());
        // 返回月份对应的数值 1-12
        System.out.println("返回月份对应的数值 1-12 : " + ldtNow.getMonthValue());

        // 返回小时
        System.out.println("返回小时: " + ldtNow.getHour());
        // 返回分钟
        System.out.println("返回分钟: " + ldtNow.getMinute());
        // 返回秒
        System.out.println("返回秒: " + ldtNow.getSecond());
        // 返回纳秒
        System.out.println("返回纳秒: " + ldtNow.getNano());

        // 当前日期减去多少天，返回新的LocalDateTime实例
        System.out.println("当前日期减去5天，返回新的LocalDateTime实例: " + ldtNow.minusDays(5));

        // 当前日期减去多少月，返回新的LocalDateTime实例
        System.out.println("当前日期减去1月，返回新的LocalDateTime实例: " + ldtNow.minusMonths(1));

        // 当前日期加上多少周，返回新的LocalDateTime实例
        System.out.println("当前日期加上1周，返回新的LocalDateTime实例: " + ldtNow.plusWeeks(1));
        // 当前日期加上多少年，返回新的LocalDateTime实例
        System.out.println("当前日期加上2年，返回新的LocalDateTime实例: " + ldtNow.plusYears(2));


        // 当前日期时间减去多少小时，返回新的LocalDateTime实例
        System.out.println("当前日期时间减去1小时，返回新的LocalDateTime实例: " + ldtNow.minusHours(1));

        // 当前日期时间减去多少分钟，返回新的LocalDateTime实例
        System.out.println("当前日期时间减去20分钟，返回新的LocalDateTime实例: " + ldtNow.minusMinutes(20));

        // 当前日期时间减去多少秒，返回新的LocalDateTime实例
        System.out.println("当前日期时间加上10秒，返回新的LocalDateTime实例: " + ldtNow.plusSeconds(10));
        // 当前日期时间减去多少纳秒，返回新的LocalDateTime实例
        System.out.println("当前日期时间加上999纳秒，返回新的LocalDateTime实例: " + ldtNow.plusNanos(999));

        // 如果早于给定的日期，则为true，否则为false
        System.out.println("当前日期是否早于2020-9-3？ " + ldtNow.isBefore(ldtOf));
        // 如果晚于给定的日期，则为true，否则为false
        System.out.println("当前日期是否晚于2020-9-3？ " + ldtNow.isAfter(ldtOf));
        // 调整小时，返回新的LocalDateTime实例
        System.out.println("调整月份到8月，返回新的LocalDateTime实例：" + ldtNow.withMonth(8));

        // with(TemporalAdjuster adjuster) 通过TemporalAdjuster去调整到对应的时间点，返回新的Instant实例
        //  调整当前日期到下个月第一天
        LocalDateTime firstDayOfNextMonth = ldtNow.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("通过TemporalAdjuster去调整到到下个月第一天：" + firstDayOfNextMonth);

        // LocalDateTime 转为 LocalDate
        LocalDate localDate = ldtNow.toLocalDate();
        System.out.println("LocalDateTime 转为 LocalDate: " + localDate);

        // LocalDateTime 转为 LocalTime
        LocalTime localTime = ldtNow.toLocalTime();
        System.out.println("LocalDateTime 转为 LocalTime: " + localTime);

        // LocalDateTime 转为 Instant
        Instant instant = ldtNow.atZone(ZoneId.systemDefault()).toInstant();
        // instant打印的是格林威治时间，会小8小时，但是毫秒数（时间戳）和当前时区的毫秒数是一样的
        System.out.println("LocalDateTime 转为 Instant: " + instant);
    }

    /**
     * 测试 Instant
     * 注意：Instant.toString() 调用的是 DateTimeFormatter.ISO_INSTANT.format(this)
     * instant打印的是格林威治时间，会小8小时，但是毫秒数（时间戳）和当前时区的毫秒数是一样的
     */
    @Test
    public void testInstant() {
        // 返回当前时间对应的 Instant 实例
        Instant instantNow = Instant.now();
        System.out.println("返回当前时间对应的 Instant 实例: " + instantNow);
        // 这将返回一个带时区的日期时间实例 ZonedDateTime，入参是系统默认时区
        ZonedDateTime zonedDateTime = instantNow.atZone(ZoneId.systemDefault());
        System.out.println("返回一个带时区的日期时间实例 ZonedDateTime，入参是系统默认时区：" + zonedDateTime);
        // 通过给定 Asia/Shanghai 时区，返回 ZonedDateTime 实例
        ZonedDateTime shZonedDateTime = instantNow.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("通过给定 Asia/Shanghai 时区，返回 ZonedDateTime 实例：" + shZonedDateTime);

        //秒内的毫秒数，从0到999
        long milliOfSecond = instantNow.getLong(ChronoField.MILLI_OF_SECOND);
        System.out.println("秒内的毫秒数，从0到999：" + milliOfSecond);

        //1970-01-01T00:00Z（ISO）开始到当前Instant实例的秒数
        long instantSeconds = instantNow.getLong(ChronoField.INSTANT_SECONDS);
        System.out.println("1970-01-01T00:00Z（ISO）开始到当前Instant实例的秒数：" + instantSeconds);

        //1970-01-01T00:00Z（ISO）开始到当前Instant实例的毫秒数
        long milli = instantNow.toEpochMilli();
        System.out.println("开始到当前Instant实例的毫秒数：" + milli);

        // 当前日期减去多少天，返回新的Instant实例
        System.out.println("当前日期减去5天，返回新的Instant实例: " + instantNow.minus(5, ChronoUnit.DAYS));

        // 当前日期减去1小时
        System.out.println("当前日期减去1小时，返回新的Instant实例: " + instantNow.minus(1, ChronoUnit.HOURS));

        // 当前日期时间减去多少秒，返回新的Instant实例
        System.out.println("当前日期时间加上10秒，返回新的Instant实例: " + instantNow.plusSeconds(10));
        // 当前日期时间减去多少纳秒，返回新的Instant实例
        System.out.println("当前日期时间加上999纳秒，返回新的Instant实例: " + instantNow.plusNanos(999));

        // 2020-9-3 对应的 Instant
        Instant otherInstant = LocalDateTime.of(2020, 9, 3, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant();
        // 如果早于给定的日期，则为true，否则为false
        System.out.println("当前日期是否早于2020-9-3？ " + instantNow.isBefore(otherInstant));
        // 如果晚于给定的日期，则为true，否则为false
        System.out.println("当前日期是否晚于2020-9-3？ " + instantNow.isAfter(otherInstant));

        // with(ChronoField.INSTANT_SECONDS, 0))：设置1970-01-01T00:00Z（ISO）开始到当前Instant实例的秒数
        // 也就是调整到 1970-01-01T00:00Z（ISO）
        System.out.println("调整到 1970-01-01T00:00Z（ISO）：" + instantNow.with(ChronoField.INSTANT_SECONDS, 0).with(ChronoField.NANO_OF_SECOND, 0));

        // Instant 转为 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instantNow, ZoneId.systemDefault());
        System.out.println("Instant 转为 LocalDateTime: " + localDateTime);

    }


    /**
     * 测试 Duration
     */
    @Test
    public void testDuration() {
        // 从给定的year, month, day, hour, minute 和 second 获取LocalDateTime的实例。
        LocalDateTime ldtOf = LocalDateTime.of(2020, 9, 4, 10, 0, 0);
        System.out.println("从给定的year, month, day, hour, minute 和 second 获取LocalDateTime的实例: " + ldtOf);
        // 从默认时区的系统时钟获取当前日期时间 LocalDateTime
        LocalDateTime ldtNow = LocalDateTime.now();
        System.out.println("从默认时区的系统时钟获取当前日期时间 LocalDateTime: " + ldtNow);

        // 返回两个时间点之间的间隔Duration
//        参数可以是：LocalTime、LocalDateTime、Instant
//        注意：LocalDate不可以作为入参
        Duration duration = Duration.between(ldtOf, ldtNow);
        // 打印 Duration 时间长度转为 天数/小时数/分钟数/毫秒数
        System.out.println("Duration 时间间隔天数：" + duration.toDays());
        System.out.println("Duration 时间间隔小时数：" + duration.toHours());
        System.out.println("Duration 时间间隔分钟数：" + duration.toMinutes());
        System.out.println("Duration 时间间隔毫秒数：" + duration.toMillis());
    }

    /**
     * 测试 Period
     */
    @Test
    public void testPeriod() {
        // 从给定的年、月和日获取LocalDate的实例。
        LocalDate ldOf = LocalDate.of(2018, 9, 3);
        System.out.println("从给定的年、月和日获取LocalDate的实例: " + ldOf);
        // 从默认时区的系统时钟获取当前日期
        LocalDate ldNow = LocalDate.now();
        System.out.println("从默认时区的系统时钟获取当前日期: " + ldNow);
        // 返回两个时间点之间的间隔Period
//        参数类型是：LocalDate
        Period period = Period.between(ldOf, ldNow);
        // 打印 Period 时间长度转为 天数/月数/年数
        System.out.println("period 实例 days 字段值：" + period.getDays());
        System.out.println("period 实例 months 字段值：" + period.getMonths());
        System.out.println("period 实例 years 字段值：" + period.getYears());

        // Period 日期间隔长度转换为总的月数
        System.out.println("Period 日期间隔长度转换为总的月数：" + period.toTotalMonths());
    }

    /**
     * 测试 TemporalAdjuster
     */
    @Test
    public void testTemporalAdjuster() {
        // 从默认时区的系统时钟获取当前日期
        LocalDate ldNow = LocalDate.now();
        System.out.println("当前日期: " + ldNow);

        // 调整到当前日期当前月份第一天
        LocalDate firstDayOfMonth = ldNow.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当前月份第一天: " + firstDayOfMonth);
        // 当前日期刚好符合是入参指定周几(DayOfWeek)，返回当前日期实例；否则调整到下一个周几，返回新日期实例
        LocalDate nextOrSameFriday = ldNow.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println("当前日期刚好符合是入参指定周五(DayOfWeek)，返回当前日期实例: " + nextOrSameFriday);
        LocalDate nextOrSameMonday = ldNow.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println("当前日期不符合是入参指定周一(DayOfWeek)，调整到下一个周一，返回新日期实例: " + nextOrSameMonday);
        // 下一个周五
        LocalDate nextFriday = ldNow.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("下一个周五: " + nextFriday);
        // 当前月份的最后一天
        LocalDate lastDayOfMonth = ldNow.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当前月份的最后一天: " + lastDayOfMonth);
        // 下一月份的第一天
        LocalDate firstDayOfNextMonth = ldNow.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("下一月份的第一天: " + firstDayOfNextMonth);

    }

    /**
     * 测试 DateTimeFormatter
     */
    @Test
    public void testDateTimeFormatter() {
        // 通过日期时间格式 pattern 构建一个 DateTimeFormatter
        DateTimeFormatter ldtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        LocalDateTime ldt = LocalDateTime.now();
        String strLdt = ldtFormat.format(ldt);// 格式化 LocalDateTime
        System.out.println("format LocalDateTime : " + strLdt);
        System.out.println("parse to LocalDateTime : " + LocalDateTime.parse("2020-10-01 08:00:06 666", ldtFormat));//解析为LocalDateTime

        DateTimeFormatter ldFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.now();
        String strLd = ldFormat.format(ld);// 格式化 LocalDateTime
        System.out.println("format LocalDate : " + strLd);
        System.out.println("parse to LocalDate : " + LocalDate.parse("2020-10-01", ldFormat));//解析为LocalDate

        DateTimeFormatter ltFormat = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
        LocalTime lt = LocalTime.now();
        String strLt = ltFormat.format(lt);// 格式化 LocalDateTime
        System.out.println("format LocalDate : " + strLt);
        System.out.println("parse to LocalTime : " + LocalTime.parse("08:00:06 666", ltFormat));//解析为LocalTime

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

    /**
     * 测试 时区
     */
    @Test
    public void testZone() {
        ZoneId systemDefaultZoneId = ZoneId.systemDefault();// 系统默认时区
        ZoneId shZoneId = ZoneId.of("Asia/Shanghai");// 上海时区

        System.out.println("系统默认时区：" + systemDefaultZoneId);
        System.out.println("上海时区：" + shZoneId);

        LocalDateTime ldt = LocalDateTime.now();
        // 这将返回一个带时区的日期时间实例 ZonedDateTime，入参是系统默认时区
        ZonedDateTime zdtFromLdt = ldt.atZone(systemDefaultZoneId);
        System.out.println(" ZonedDateTime from LocalDateTime：" + zdtFromLdt);

        Instant instant = Instant.now();
        // 这将返回一个带时区的日期时间实例 ZonedDateTime，入参是上海时区
        ZonedDateTime zdtFromInstant = instant.atZone(shZoneId);
        System.out.println(" ZonedDateTime from Instant In Asia/Shanghai ：" + zdtFromInstant);


        // 获取所有时时区信息
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        // 打印所有时区信息
        System.out.println("所有时区信息: ");
        availableZoneIds.forEach(System.out::println);
    }
}
