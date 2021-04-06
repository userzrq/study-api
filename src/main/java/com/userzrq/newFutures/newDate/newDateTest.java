package com.userzrq.newFutures.newDate;

import javax.sound.midi.Soundbank;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Jdk8 时间新API
 */
public class newDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();  // 10:44:04.742

        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        LocalDateTime dateTime = LocalDateTime.of(2019, 11, 23, 23, 59);
        System.out.println(dateTime.getDayOfYear());
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getDayOfMonth());

        // 获取本初子午线的标准时间
        Instant now = Instant.now();    // UTC时间
        System.out.println(now);

        // 东八区的时间 偏移量
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 距离1970年1月1日0:0:0(UTC) 开始的毫秒数
        long l = now.toEpochMilli();
        System.out.println(l);

        // 根据偏移量获取时间
        Instant instant = Instant.ofEpochMilli(l);
        System.out.println(instant);


    }
}
