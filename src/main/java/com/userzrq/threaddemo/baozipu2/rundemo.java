package com.userzrq.threaddemo.baozipu2;




public class rundemo {
    public static void main(String[] args) {

        BaoZi baoZi = new BaoZi();

        Consumer consumer = new Consumer(baoZi);

        Producer producer = new Producer(baoZi);

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(consumer);
//        executorService.submit(producer);

        producer.run();
        consumer.run();


    }
}
