package com.userzrq.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 使用AtomicStampedRerence解决ABA的问题
 *
 * @author 10017
 */
public class Demo05 {

    static int accountMonry = 19;

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(accountMonry, 0);

    static void recharge() {
        for (int i = 0; i < 2; i++) {
            int stamp = money.getStamp();
            new Thread(() -> {
                for (int j = 0; j < 50; j++) {
                    Integer m = money.getReference();
                    if (m == accountMonry) {
                        if (money.compareAndSet(m, m + 20, stamp, stamp + 1)) {
                            System.out.println("当前时间戳：" + money.getStamp() + ",当前余额：" + m + "，小于20，充值20元成功，余额：" + money.getReference() + "元");
                        }
                    }
                }

                // 休眠100ms
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    static void consume() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            int stamp = money.getStamp();
            Integer m = money.getReference();
            if (m > 20) {
                if (money.compareAndSet(m, m - 20, stamp, stamp + 1)) {
                    System.out.println("当前时间戳：" + money.getStamp() + ",当前余额：" + m + "，大于10，成功消费10元，余额：" + money.getReference() + "元");
                }
            }
            // 休眠50ms
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        recharge();
        consume();
    }
}
