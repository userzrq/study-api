package com.userzrq.api;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class RedissonTest {

    @Autowired
    private RedissonClient redisson;

    @Test
    public void SemaphoreTest() {

        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        semaphore.trySetPermits(3);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]正在尝试获取分布式锁");
                        semaphore.acquire();
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]成功获取到了Semaphore锁，开始工作");
                        Thread.sleep(3000);
                        semaphore.release();
                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]释放Semaphore锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
