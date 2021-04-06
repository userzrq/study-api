package com.userzrq.threadLimit;


import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 漏桶算法
 */
public class Demo02 {

    public static class BucketLimit {

        static AtomicInteger threadNum = new AtomicInteger(1);
        // 容量
        private int capacity;
        // 流速
        private int flowRate;
        // 流速时间单位
        private TimeUnit flowRateUnit;
        // 漏桶流出的任务时间间隔（纳秒）
        private long flowRateNanosTime;

        public BucketLimit(int capacity, int flowRate, TimeUnit flowRateUnit) {
            this.capacity = capacity;
            this.flowRate = flowRate;
            this.flowRateUnit = flowRateUnit;
            this.bucketThreadWork();
        }

        // 漏桶线程
        public void bucketThreadWork() {

        }

        // 漏桶线程开始工作
        public void bucketWork() {
            while (true) {
                // 阻塞队列返回头部第一个对象，如果无对象不会报错，返回null
                Node node = this.queue.poll();
                if (Objects.nonNull(node)) {
                    // 唤醒对象中的线程对象
                    LockSupport.unpark(node.thread);
                }
                //休眠flowRateNanosTime
                LockSupport.parkNanos(flowRateNanosTime);
            }
        }


        private BlockingQueue<Node> queue;

    }

    /**
     * 漏桶中存放的元素
     */
    public class Node {
        private Thread thread;

        public Node(Thread thread) {
            this.thread = thread;
        }
    }


    public static void main(String[] args) {

    }
}
