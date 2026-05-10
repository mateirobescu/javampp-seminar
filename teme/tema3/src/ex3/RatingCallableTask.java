package ex3;

import java.util.concurrent.Callable;

public class RatingCallableTask implements Callable<RatingSatistics> {
    private double[] arr;
    private int startIdx;
    private int stopIdx;
    private RatingSatistics rs;

    public RatingCallableTask(double[] arr, int threaCount, int threadNo) {
        this(arr, threaCount, threadNo, "No name");
    }

    public RatingCallableTask(double[] arr, int threaCount, int threadNo, String name) {
        this.arr = arr;
        this.startIdx = (arr.length / threaCount) * threadNo;
        this.stopIdx = (arr.length / threaCount) * (threadNo + 1) - 1;
        this.rs = new RatingSatistics(name);
    }

    @Override
    public RatingSatistics call() throws Exception {
        for(int i = this.startIdx; i <= this.stopIdx; i++)
            rs.update(this.arr[i]);

        return rs;
    }
}
