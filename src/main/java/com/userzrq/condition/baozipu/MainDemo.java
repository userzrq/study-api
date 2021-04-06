package com.userzrq.condition.baozipu;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        List<Baozi> baozis = new ArrayList<>();

        Consumer consumer1 = new Consumer(baozis);
        Consumer consumer2 = new Consumer(baozis);
        Consumer consumer3 = new Consumer(baozis);

        Producer producer1 = new Producer(baozis);


        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);
        executorService.execute(producer1);

    }
}
