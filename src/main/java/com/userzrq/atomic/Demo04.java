package com.userzrq.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类型原子类,原子引用多个变量
 * <p>
 * AtomicReference：引用类型原子类
 * AtomicStampedRerence：原子更新引用类型里的字段原子类
 * AtomicMarkableReference ：原子更新带有标记位的引用类型
 * <p>
 * 有一家蛋糕店，为了挽留客户，决定为贵宾卡客户一次性赠送20元，刺激客户充值和消费，但条件是，每一位客户只能被赠送一次
 *
 * @author 10017
 */
public class Demo04 {

    // 账户原始余额
    static volatile int accoutMoney = 19;
    /**
     * 用于对账户余额做原子操作
     */
    static AtomicReference<Integer> money = new AtomicReference<>(accoutMoney);

    /**
     * 模拟2个线程同时更新后台数据库，为用户充值
     */
    static void recharge() {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Integer m = money.get();
                    if (m == accoutMoney) {
                        if (money.compareAndSet(m, m + 20)) {
                            System.out.println("当前余额：" + m + "，小于20，充值20元成功，余额：" + money.get() + "元");
                        }
                    }
                    // 休眠100ms
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    static void consume() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Integer m = money.get();
            if (m > 20) {
                if (money.compareAndSet(m, m - 20)) {
                    System.out.println("当前余额：" + m + "，大于10，成功消费10元，余额：" + money.get() + "元");
                }
            }
            // 休眠50ms
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        recharge();
        consume();
        //当前余额：19，小于20，充值20元成功，余额：39元
        //当前余额：39，大于10，成功消费10元，余额：19元
        //当前余额：19，小于20，充值20元成功，余额：39元
        //当前余额：39，大于10，成功消费10元，余额：19元
        //当前余额：19，小于20，充值20元成功，余额：39元
        //当前余额：39，大于10，成功消费10元，余额：19元
        //当前余额：19，小于20，充值20元成功，余额：39元

        //从输出中可以看到，这个账户被先后反复多次充值。其原因是账户余额被反复修改，修改后的值和原有的数值19一样，使得CAS操作无法正确判断当前数据是否被修改过（是否被加过20）。
        // 虽然这种情况出现的概率不大，但是依然是有可能出现的，因此，当业务上确实可能出现这种情况时，我们必须多加防范。
    }
}
