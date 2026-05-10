package ex3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class RatingForkJoinTask extends ForkJoinTask<RatingSatistics> {
    private double[] arr;
    private int startIdx;
    private int stopIdx;
    private RatingSatistics rs;

    private RatingForkJoinTask(double[] arr) {
        this(arr, "No name");
    }

    private RatingForkJoinTask(double[] arr, String name) {
        this(arr, name, 0, arr.length - 1);
    }

    private RatingForkJoinTask(double[] arr,  int startIdx, int stopIdx) {
        this(arr, "No name", startIdx, stopIdx);
    }

    private RatingForkJoinTask(double[] arr, String name, int startIdx, int stopIdx) {
        super();
        this.arr = arr;
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;
        this.rs = new RatingSatistics(name);
    }

    @Override
    public RatingSatistics getRawResult() {
        return rs;
    }

    @Override
    protected void setRawResult(RatingSatistics value) {
        this.rs = value;
    }

    @Override
    protected boolean exec() {
        if(stopIdx - startIdx <= 10_000 ) {
            for(int i = startIdx; i <= stopIdx; i++)
                rs.update(arr[i]);

            return true;
        }

        int mid = this.startIdx + (this.stopIdx - this.startIdx) / 2;
        RatingForkJoinTask left = new RatingForkJoinTask(arr, this.startIdx, mid);
        RatingForkJoinTask right = new RatingForkJoinTask(arr, mid + 1, this.stopIdx);

        left.fork();
        right.fork();

        rs.merge(left.join()).merge(right.join());

        return true;
    }

    public static RatingSatistics start(double[] arr, String name) {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        return pool.invoke(new RatingForkJoinTask(arr, name));
    }
}
