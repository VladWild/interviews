package forkjoinpool.invokeAll;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinPoolInvokeAllExampleMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Aggreagator aggreagator = new Aggreagator();

        System.out.println("start");
        long start = System.currentTimeMillis();
        Integer result = aggreagator.doRequest();

        System.out.println(System.currentTimeMillis() - start); // ~ 7 sec
        System.out.println(result); // 8
    }
}

class Aggreagator {
    private SystemA systemA = new SystemA();
    private SystemB systemB = new SystemB();

    Integer doRequest() throws ExecutionException, InterruptedException { // 7 sec

        ForkJoinPool forkJoinPool = new ForkJoinPool(2);

        List<Callable<Integer>> tasks = Arrays.asList(
                () -> systemA.doRequest(),
                () -> systemB.doRequest()
        );

        List<Future<Integer>> futures = forkJoinPool.invokeAll(tasks);

        return aggregate(futures.get(0).get(), futures.get(1).get());
    }

    Integer aggregate(Integer responseA, Integer responseB) {
        // ...
        return responseA + responseB;
    }
}

class SystemA {

    int doRequest() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }
}

class SystemB {

    int doRequest() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 6;
    }
}