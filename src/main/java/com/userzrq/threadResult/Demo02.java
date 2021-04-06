package com.userzrq.threadResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo02 {

    static class Result<T> {
        T result;

        public void setResult(T result) {
            this.result = result;
        }

        public T getResult() {
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());

        CountDownLatch countDownLatch = new CountDownLatch(5);

        Result<Integer> result = new Result<>();

        result.setResult(10);
        for (int i = 5; i > 0; i--) {
            TimeUnit.SECONDS.sleep(1);
            countDownLatch.countDown();
        }

        countDownLatch.await();
        Integer rs = result.getResult();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + ":" + rs);
    }
}
