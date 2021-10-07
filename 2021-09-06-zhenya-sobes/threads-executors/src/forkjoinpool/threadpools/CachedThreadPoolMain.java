package forkjoinpool.threadpools;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CachedThreadPoolMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        Runnable runnable = () -> {
            /*try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            copyOnWriteArrayList.add(Thread.currentThread().getName());
        };

        for (int i = 0; i < 20; i++) {
            cachedThreadPool.submit(runnable);
        }

        cachedThreadPool.shutdown();

        Thread.sleep(1000);

        Map<String, List<String>> collect = copyOnWriteArrayList.stream()
                .collect(Collectors.groupingBy(Function.identity()));
        collect.forEach((k, v) -> System.out.println(k + " " + v.size()));
    }
}


