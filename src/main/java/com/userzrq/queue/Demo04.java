package com.userzrq.queue;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Demo04 {

    static DelayQueue<Msg> pushQueue = new DelayQueue<Msg>();

    static {
        new Thread(() -> {
            while (true) {
                Msg msg;

                try {
                    msg = pushQueue.take();
                    long endTime = System.currentTimeMillis();
                    System.out.println(String.format("定时发送时间：%s,实际发送时间：%s,发送消息:%s", msg.sendTimeMs, endTime, msg));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Msg implements Delayed {

        private Integer priority;

        private String msg;
        // 定时发送时间，毫秒格式
        private long sendTimeMs;

        public Msg(int priority, String msg, long sendTimeMs) {
            this.priority = priority;
            this.msg = msg;
            this.sendTimeMs = sendTimeMs;
        }

        /**
         * 重写getDelay方法，定义任务的延时执行时间
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.sendTimeMs - Calendar.getInstance().getTimeInMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o instanceof Msg) {
                Msg c2 = (Msg) o;
                return Integer.compare(this.priority, c2.priority);
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    ", sendTimeMs=" + sendTimeMs +
                    '}';
        }
    }

    public static void pushMsg(int priority, String msg, long sendTimeMs) {
        pushQueue.put(new Msg(priority, msg, sendTimeMs));
    }

    public static void main(String[] args) {
        for (int i = 5; i >= 1; i--) {
            String msg = "一起来学java高并发,第" + i + "天";

            pushMsg(i, msg, Calendar.getInstance().getTimeInMillis() + i * 2000);
        }
    }
}
