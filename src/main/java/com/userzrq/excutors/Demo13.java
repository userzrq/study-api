package com.userzrq.excutors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author 10017
 */
public class Demo13 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<Integer>> list = new ArrayList<>();

        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                return j;
            });
        }

        solve(executorService, list, (a) -> {
            System.out.println(System.currentTimeMillis() + ":" + a);
        });
        executorService.shutdown();
    }

    public static <T> void solve(ExecutorService executorService, Collection<Callable<T>> tasks, Consumer<T> consumer) throws InterruptedException, ExecutionException {
        ExecutorCompletionService<T> ecs = new ExecutorCompletionService<>(executorService);

        for (Callable<T> task : tasks) {
            ecs.submit(task);
        }
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            // 阻塞式等待任务依次完成,判非空后消费
            T t = ecs.take().get();
            if (!Objects.isNull(t)) {
                consumer.accept(t
                );
            }
        }


    }
}
