package forkjoinpool.submit.forkjointask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolExampleMain {

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

        ForkJoinTask<Integer> submit1 = forkJoinPool.submit(() -> systemA.doRequest());
        ForkJoinTask<Integer> submit2 = forkJoinPool.submit(() -> systemB.doRequest());

        List<Integer> integers = Arrays.asList(submit1.get(), submit2.get());

        return aggregate(integers.get(0), integers.get(1));
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