package com.userzrq.java8.transaction;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        TaskManager transactionManager = new TaskManager();

        // 创建4个任务

        Task a = new Task() {
            @Override
            public Object call() throws Exception {
                System.out.println("task A start ......");
                Thread.sleep(2000);
                System.out.println("task A ......");
                Thread.sleep(2000);
                return null;
            }

            @Override
            public void rollback() {
                System.out.println("task A rollback");
            }
        };

        Task b = new Task() {
            @Override
            public Object call() throws InterruptedException {
                System.out.println("task B start ......");
                Thread.sleep(2000);
                int a = 1 / 0;
                System.out.println("task B ......");
                Thread.sleep(2000);
                return null;
            }

            @Override
            public void rollback() {
                System.out.println("task B rollback");
            }
        };

        Task c = new Task() {
            @Override
            public Object call() throws Exception {
                System.out.println("task C start ......");
                Thread.sleep(5000);
                System.out.println("task C ......");
                Thread.sleep(1000);
                return null;
            }

            @Override
            public void rollback() {
                System.out.println("task C rollback");
            }
        };


        Task d = new Task() {
            @Override
            public Object call() throws Exception {
                System.out.println("task D start ......");
                Thread.sleep(500);
                System.out.println("task D ......");
                Thread.sleep(1000);
                return null;
            }

            @Override
            public void rollback() {
                System.out.println("task D rollback");
            }
        };

        transactionManager.addTask(a);
        transactionManager.addTask(b);
        transactionManager.addTask(c);
        transactionManager.addTask(d);
        transactionManager.done();
    }
}
