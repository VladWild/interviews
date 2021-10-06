package forkjoinpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        forkJoinPool.submit(() -> "Hello!!!!!!");

        //ForkJoinPool
        Executors.newWorkStealingPool();

        //ThreadPoolExecutor
        Executors.newFixedThreadPool(2);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(1000);
    }
}
