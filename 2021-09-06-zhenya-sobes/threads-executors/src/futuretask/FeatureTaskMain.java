package futuretask;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FeatureTaskMain {

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

        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> systemA.doRequest());
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> systemB.doRequest());

        new Thread(futureTask1).start();
        new Thread(futureTask2).start();

        List<Integer> integers = Arrays.asList(futureTask1.get(), futureTask2.get());

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