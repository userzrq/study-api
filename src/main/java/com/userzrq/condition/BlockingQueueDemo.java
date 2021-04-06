package com.userzrq.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同一把锁创建多个Condition,模拟阻塞队列
 * <p>
 * 同一个对象上的
 */
public class BlockingQueueDemo<E> {
    /**
     * 阻塞队列的最大容量
     */
    int size;

    LinkedList<E> list = new LinkedList<>();

    static ReentrantLock lock = new ReentrantLock();
    /**
     * 队列满时的等待条件
     */
    Condition notFull = lock.newCondition();
    /**
     * 队列空时的等待条件
     */
    Condition notEmpty = lock.newCondition();

    public BlockingQueueDemo(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        // 对ReentrantLock的操作首先得获取锁
        lock.lock();
        try {
            while (list.size() == size) {
                // 如果队列满了，那notFull 的Condition一直处于阻塞状态
                notFull.await();
            }
            // 队列还没满的时候
            list.add(e);
            System.out.println("元素入队" + e);
            // 队列中有元素了,可以解开对队列无元素Condition的阻塞
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.size() == 0) {
                notEmpty.await();
            }
            // 出队，移除链表首个元素
            e = list.removeFirst();
            System.out.println("被移除的元素" + e);
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        BlockingQueueDemo<Integer> queue = new BlockingQueueDemo<>(2);


        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(() -> {
                try {
                    queue.enqueue(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    queue.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
