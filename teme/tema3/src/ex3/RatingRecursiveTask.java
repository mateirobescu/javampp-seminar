package ex3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RatingRecursiveTask extends RecursiveTask<RatingSatistics> {
    private double[] arr;
    private int startIdx;
    private int stopIdx;
    private RatingSatistics rs;

    private RatingRecursiveTask(double[] arr) {
        this(arr, "No name");
    }

    private RatingRecursiveTask(double[] arr, String name) {
        this(arr, name, 0, arr.length - 1);
    }

    private RatingRecursiveTask(double[] arr, int startIdx, int stopIdx) {
        this(arr, "No name", startIdx, stopIdx);
    }

    private RatingRecursiveTask(double[] arr, String name, int startIdx, int stopIdx) {
        super();
        this.arr = arr;
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;
        this.rs = new RatingSatistics(name);
    }

    @Override
    protected RatingSatistics compute() {
        if(stopIdx - startIdx <= 10_000 ) {
            for(int i = startIdx; i <= stopIdx; i++)
                rs.update(arr[i]);

            return rs;
        }

        int mid = this.startIdx + (this.stopIdx - this.startIdx) / 2;
        RatingRecursiveTask left = new RatingRecursiveTask(arr, this.startIdx, mid);
//        RatingRecursiveTask right = new RatingRecursiveTask(arr, mid + 1, this.stopIdx);

        left.fork();
        this.startIdx = mid + 1;
        this.compute();

        rs.merge(left.join());
        return rs;
    }

    public static RatingSatistics start(double[] arr, String name) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        return pool.invoke(new RatingRecursiveTask(arr, name));
    }
}
