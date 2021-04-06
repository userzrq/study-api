package com.userzrq.threadResult;

import java.util.concurrent.TimeUnit;

public class Demo01 {

    /**
     * 结果集对象
     *
     * @param <T>
     */
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

        Result<Integer> result = new Result<>();

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                result.setResult(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();

        // 在主线程中对thread线程进行
        //thread.join();

        Thread.currentThread().join();

        //获取thread线程的执行结果
        Integer rs = result.getResult();
        System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() + ":" + rs);

    }
}
