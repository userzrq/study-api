package com.userzrq.excutors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Demo12 {

//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 用Executors创建线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        List<Callable<Integer>> list = new ArrayList<>();
//        int taskCount = 5;
//
//        for (int i = taskCount; i > 0; i--) {
//            int j = i * 2;
//            list.add(() -> {
//                TimeUnit.SECONDS.sleep(j);
//                return j;
//            });
//        }
//
//        // 补充consumer的consume方法 （lambda表达式）
//        solve(executorService, list, a -> {
//            System.out.println(System.currentTimeMillis() + ":" + a);
//        });
//
//        executorService.shutdown();
//    }
//
//    public static <T> void solve(Executor executor, Collection<Callable<T>> solvers, Consumer<T> user) throws InterruptedException, ExecutionException {
//        CompletionService<T> ecs = new ExecutorCompletionService<T>(executor);
//        for (Callable<T> solver : solvers) {
//            ecs.submit(solver);
//        }
//
//        int size = solvers.size();
//        for (int i = 0; i < size; i++) {
//            T t = ecs.take().get();
//            if (!Objects.isNull(t)) {
//                user.accept(t);
//            }
//        }
//    }
}
