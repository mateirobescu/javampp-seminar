package eu.ase.threads.paralel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProgMainThreadParallel {

    private static final int NTHREADS = 4;

    public static void main(String[] args) {
        int dimVect = 40_000_000;
        int[] v = new int[dimVect];
        Long sum = 0L; //Long.valueOf(0);

        for(int i = 0; i < dimVect; i++) {
            v[i] = i + 1;
        }

        long startTime = 0L;
        long stopTime = 0L;
        int startIdx = 0;
        int stopIdx = 0;

        startTime = System.currentTimeMillis();
        for(int i = 0; i < dimVect; i++) {
            sum += v[i];
        }
        stopTime = System.currentTimeMillis();
        System.out.println("1. Seq time = " + (stopTime - startTime) + ", suma = " + sum);

        startTime = System.currentTimeMillis();
        Thread[] vectThreads = new Thread[NTHREADS];
        MyMultiThreadArray[] vectTasks = new MyMultiThreadArray[NTHREADS];

        for(int i = 0; i < NTHREADS; i++) {
            startIdx = i * (dimVect / NTHREADS);
            stopIdx = (i + 1) * (dimVect / NTHREADS) - 1;
            vectTasks[i] = new MyMultiThreadArray(v, startIdx, stopIdx);
            vectThreads[i] = new Thread(vectTasks[i]);
        }

        for(int i = 0; i < NTHREADS; i++) {
            vectThreads[i].start();
        }

        for(int i = 0; i < NTHREADS; i++) {
            try {
                vectThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sum = 0L;
        for(int i = 0; i < NTHREADS; i++) {
            sum += vectTasks[i].getSum();
        }
        stopTime = System.currentTimeMillis();
        System.out.println("2. Multithread standard time = " + (stopTime - startTime) + ", suma = " + sum);

        //3.
        sum = 0L;
        startTime = System.currentTimeMillis();
        ExecutorService execThreadPool = Executors.newFixedThreadPool(NTHREADS);
        MyMultiThreadArray[] workerTask = new MyMultiThreadArray[NTHREADS];

        for(int i = 0; i < NTHREADS; i++) {
            startIdx = i * (dimVect/NTHREADS);
            stopIdx = (i + 1) * (dimVect/NTHREADS) - 1;
            workerTask[i] = new MyMultiThreadArray(v, startIdx, stopIdx);
            execThreadPool.execute(workerTask[i]);
        }

        try {
            execThreadPool.shutdownNow();
            execThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < NTHREADS; i++) {
            sum += workerTask[i].getSum();
        }
        stopTime = System.currentTimeMillis();
        System.out.println("3. Multithread ExecutorService time = " + (stopTime - startTime) + ", suma = " + sum);

        // 4.
        startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Long>> futures = new ArrayList<>();

        for(int i = 0; i < NTHREADS; i++) {
            startIdx = i * (dimVect/NTHREADS);
            stopIdx = (i + 1) * (dimVect/NTHREADS) - 1;

            Callable<Long> worker = new MyCallableArray(v, startIdx, stopIdx);
            Future<Long> future = executor.submit(worker);
            futures.add(future);
        }
        sum = 0L;
        for(Future<Long> future : futures) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            executor.shutdownNow();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("4. Array multithreading - ExecutorService - Callable & Future - time = " + (stopTime - startTime) + ", suma = " + sum);

        // 5. Fork join
        startTime = System.currentTimeMillis();

        sum = SumForkJoin.sumArrays(v);

        stopTime = System.currentTimeMillis();
        System.out.println("5. Fork-Join time = " + (stopTime - startTime) + ", suma = " + sum);

        // 6.
        sum = 0L;
        startTime = System.currentTimeMillis();
        MyMultiThreadArray[] vectVirtualThreads = new MyMultiThreadArray[NTHREADS];
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i = 0; i < NTHREADS; i++) {
                startIdx = i * (dimVect/NTHREADS);
                stopIdx = (i + 1) * (dimVect/NTHREADS) - 1;

                vectVirtualThreads[i] = new MyMultiThreadArray(v, startIdx, stopIdx);
                executorService.execute(vectVirtualThreads[i]);
            }
        }

        for(int i = 0; i < NTHREADS; i++) {
            sum += vectVirtualThreads[i].getSum();
        }

        stopTime = System.currentTimeMillis();
        System.out.println("6. Virtual threads time = " + (stopTime - startTime) + ", suma = " + sum);
    }
}
