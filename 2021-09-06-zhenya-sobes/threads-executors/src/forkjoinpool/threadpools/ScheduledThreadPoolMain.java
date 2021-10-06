package forkjoinpool.threadpools;

import java.util.concurrent.*;

public class ScheduledThreadPoolMain {

    public static volatile long start;

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);

        start = System.currentTimeMillis();

        scheduledThreadPool.schedule(() ->  {
            System.out.println("Пока");
            System.out.println(System.currentTimeMillis() - start);
        }, 5, TimeUnit.SECONDS);

        scheduledThreadPool.schedule(() -> {
            System.out.println("Как дела?");
            System.out.println(System.currentTimeMillis() - start);
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.schedule(() -> {
            System.out.println("Привет");
            System.out.println(System.currentTimeMillis() - start);
        }, 2, TimeUnit.SECONDS);

        scheduledThreadPool.shutdown();
    }
}


