package com.userzrq.queue;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Demo02 {

    static PriorityBlockingQueue<Msg> pushQueue = new PriorityBlockingQueue<Msg>();

    static {
        new Thread(() -> {
            while (true) {
                Msg msg;
                try {
                    long startTime = System.currentTimeMillis();
                    msg = pushQueue.take();
                    TimeUnit.MILLISECONDS.sleep(100);
                    long endTime = System.currentTimeMillis();
                    System.out.println(String.format("[%s,%s,take耗时:%s],%s,发送消息:%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName(), msg));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    static class Msg implements Comparable<Msg> {
        // 优先级 数值越小优先级越高
        private int priority;
        // 推送的信息
        private String msg;

        public Msg(int priority, String msg) {
            this.priority = priority;
            this.msg = msg;
        }

        @Override
        public int compareTo(Msg o) {
            return Integer.compare(this.priority, o.priority);
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    public static void pushMsg(int priority, String msg) {
        pushQueue.put(new Msg(priority, msg));
    }

    public static void main(String[] args) {
        for (int i = 5; i >= 1; i--) {
            String msg = "一起来学java高并发,第" + i + "天";
            pushMsg(i, msg);
        }
    }
}
