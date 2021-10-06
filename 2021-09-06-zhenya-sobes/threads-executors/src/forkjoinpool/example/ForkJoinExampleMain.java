package forkjoinpool.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExampleMain {

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        final ForkJoinTask<Integer> result = forkJoinPool.submit(new FibonacciForkJoin(20));
        System.out.println("The result FibonacciForkJoin is : " + result.join());
        System.out.println(System.currentTimeMillis() - start);

        final ForkJoinPool forkJoinPool2 = new ForkJoinPool();
        long start2 = System.currentTimeMillis();
        final ForkJoinTask<Integer> result2 = forkJoinPool2.submit(new FibonacciCompute(20));
        System.out.println("The result FibonacciCompute is : " + result2.join());
        System.out.println(System.currentTimeMillis() - start2);
    }

    static class FibonacciForkJoin extends RecursiveTask<Integer> {

        private final int number;

        public FibonacciForkJoin(int number) {
            this.number = number;
        }

        @Override
        protected Integer compute() {
            if (number <= 1) {
                return number;
            } else {
                FibonacciForkJoin fibonacciMinus1 = new FibonacciForkJoin(number - 1);
                FibonacciForkJoin fibonacciMinus2 = new FibonacciForkJoin(number - 2);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fibonacciMinus1.fork();
                fibonacciMinus2.fork();
                return fibonacciMinus2.join() + fibonacciMinus1.join();
            }
        }
    }

    static class FibonacciCompute extends RecursiveTask<Integer> {

        private final int number;

        public FibonacciCompute(int number) {
            this.number = number;
        }

        @Override
        protected Integer compute() {
            if (number <= 1) {
                return number;
            } else {
                FibonacciForkJoin fibonacciMinus1 = new FibonacciForkJoin(number - 1);
                FibonacciForkJoin fibonacciMinus2 = new FibonacciForkJoin(number - 2);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return fibonacciMinus1.compute() + fibonacciMinus2.compute();
            }
        }
    }
}


