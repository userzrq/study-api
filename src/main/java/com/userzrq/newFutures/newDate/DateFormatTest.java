package com.userzrq.newFutures.newDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class DateFormatTest {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime now = LocalDateTime.now();
        System.out.println(formatter.format(now));
        System.out.println(now);

        TemporalAccessor parse = formatter.parse("2019-04-14T11:26:07.555");
        System.out.println(parse);


        DateTimeFormatter dateTime = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        System.out.println(dateTime.format(now));

        // 类中包含了所有的时区信息
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for (String zoneId : zoneIds) {
            System.out.println(zoneId);
        }

        LocalDateTime tokyoTime = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(tokyoTime);

        // 获取本时区的ZonedDateTime对象   2020-12-01T11:13:01.870+08:00[Asia/Shanghai]
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        // 获取指定时区的ZonedDateTime对象  2020-12-01T12:13:01.871+09:00[Asia/Tokyo]
        ZonedDateTime tokyoZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(tokyoZonedDateTime);


        // TemporalAdjuster:时间校正器
        // 获取当前日期的下一个周日是哪天？
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
        LocalDateTime localDateTime = LocalDateTime.now().with(temporalAdjuster);
        System.out.println(localDateTime);
        // 获取下一个工作日是哪天？
        LocalDate localDate = LocalDate.now().with(new TemporalAdjuster() {
            @Override
            public Temporal adjustInto(Temporal temporal) {
                LocalDate date = (LocalDate) temporal;
                if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                    return date.plusDays(3);
                } else if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                    return date.plusDays(2);
                } else {
                    return date.plusDays(1);
                }
            }
        });
        System.out.println("下一个工作日是：" + localDate);
    }
}
