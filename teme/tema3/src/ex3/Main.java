package ex3;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {
    static void main(String[] args) {
        final int NO_THREADS = 4;
        final int NO_RATINGS = 40_000_000;
        List<String> results = new ArrayList<>();
//        double[] ratings = new double[40_000_000];
        double[] ratings = new Random().doubles(NO_RATINGS).map(d -> d * 4 + 1).toArray();

        long startTime = System.currentTimeMillis();
        RatingSatistics rsSequential = new RatingSatistics("Sequential");
        for (double rating : ratings)
            rsSequential.update(rating);
        long stopTime = System.currentTimeMillis();
        results.add(rsSequential.print(stopTime - startTime));
        System.out.println(results.getLast());

        startTime = System.currentTimeMillis();
        RatingSatistics rsStreams = new RatingSatistics("Streams");
        Arrays.stream(ratings).forEach(rsStreams::update);
        stopTime = System.currentTimeMillis();
        results.add(rsStreams.print(stopTime - startTime));
        System.out.println(results.getLast());


        startTime = System.currentTimeMillis();
        RatingSatistics rsParallelStream = new RatingSatistics("Parallel Streams");
        rsParallelStream.merge(Arrays.stream(ratings).parallel().collect(
                RatingSatistics::new,
                RatingSatistics::update,
                RatingSatistics::merge
        ));

        stopTime = System.currentTimeMillis();
        results.add(rsParallelStream.print(stopTime - startTime));
        System.out.println(results.getLast());


        startTime = System.currentTimeMillis();
        RatingSatistics rsThreadRunnable = new RatingSatistics("ThreadRunnable");

        RatingTask[] tasks = new RatingTask[NO_THREADS];
        Thread[] threads = new Thread[NO_THREADS];
        for(int i = 0; i < NO_THREADS; i++) {
            tasks[i] = new RatingTask(ratings, NO_THREADS, i);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for(int i = 0; i < NO_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            rsThreadRunnable.merge(tasks[i].getStats());
        }

        stopTime = System.currentTimeMillis();
        results.add(rsThreadRunnable.print(stopTime-startTime));
        System.out.println(results.getLast());


        startTime = System.currentTimeMillis();
        RatingSatistics rsExecutorService = new RatingSatistics("ExecutorService");

        RatingTask[] tasksExecutor = new RatingTask[NO_THREADS];
        ExecutorService executor = Executors.newFixedThreadPool(NO_THREADS);
        for(int i = 0; i < NO_THREADS; i++) {
            tasksExecutor[i] = new RatingTask(ratings, NO_THREADS, i);
            executor.execute(tasksExecutor[i]);
        }

        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            if(!executor.isTerminated())
                executor.shutdownNow();
        }

        for(RatingTask rt : tasksExecutor)
            rsExecutorService.merge(rt.getStats());

        stopTime = System.currentTimeMillis();
        results.add(rsExecutorService.print(stopTime-startTime));
        System.out.println(results.getLast());

        startTime = System.currentTimeMillis();
        RatingSatistics rsCallable = new RatingSatistics("Callable");

        List<Future<RatingSatistics>> futures = new ArrayList<>(NO_THREADS);
        ExecutorService executorCallable = Executors.newFixedThreadPool(NO_THREADS);
        for(int i = 0; i < NO_THREADS; i++) {
            futures.add(executorCallable.submit(new RatingCallableTask(ratings, NO_THREADS, i)));
        }

        try {
            executorCallable.shutdown();
            executorCallable.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            if(!executorCallable.isTerminated())
                executorCallable.shutdownNow();
        }

        for(Future<RatingSatistics> rt : futures) {
            try {
                rsCallable.merge(rt.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        stopTime = System.currentTimeMillis();
        results.add(rsCallable.print(stopTime-startTime));
        System.out.println(results.getLast());


        startTime = System.currentTimeMillis();
        RatingSatistics rsFork = RatingForkJoinTask.start(ratings, "Fork Join");
        stopTime = System.currentTimeMillis();
        results.add(rsFork.print(stopTime-startTime));
        System.out.println(results.getLast());


        startTime = System.currentTimeMillis();
        RatingSatistics rsRecursive = RatingRecursiveTask.start(ratings, "Recursive");
        stopTime = System.currentTimeMillis();
        results.add(rsRecursive.print(stopTime-startTime));
        System.out.println(results.getLast());


        startTime = System.currentTimeMillis();
        RatingSatistics rsVirtual = new RatingSatistics("Virtual Threads");

        List<Future<RatingSatistics>> futuresVirtual = new ArrayList<>(NO_THREADS);
        ExecutorService executorVirtual = Executors.newVirtualThreadPerTaskExecutor();
        for(int i = 0; i < NO_THREADS; i++) {
            futuresVirtual.add(executorVirtual.submit(new RatingCallableTask(ratings, NO_THREADS, i)));
        }

        try {
            executorVirtual.shutdown();
            executorVirtual.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            if(!executorVirtual.isTerminated())
                executorVirtual.shutdownNow();
        }

        for(Future<RatingSatistics> rt : futuresVirtual) {
            try {
                rsVirtual.merge(rt.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        stopTime = System.currentTimeMillis();
        results.add(rsVirtual.print(stopTime-startTime));
        System.out.println(results.getLast());

        results.stream().map(stats -> stats.split("\\R")[0]).forEach(System.out::println);

    }
}
