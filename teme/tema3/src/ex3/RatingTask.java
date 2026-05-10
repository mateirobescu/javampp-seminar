package ex3;

public class RatingTask implements Runnable {
    private double[] arr;
    private int startIdx;
    private int stopIdx;
    private RatingSatistics rs;

    public RatingTask(double[] arr, int threaCount, int threadNo) {
        this(arr, threaCount, threadNo, "No name");
    }

    public RatingTask(double[] arr, int threaCount, int threadNo, String name) {
        this.arr = arr;
        this.startIdx = (arr.length / threaCount) * threadNo;
        this.stopIdx = (arr.length / threaCount) * (threadNo + 1) - 1;
        this.rs = new RatingSatistics(name);
    }

    @Override
    public void run() {
        for(int i = this.startIdx; i <= this.stopIdx; i++)
            rs.update(this.arr[i]);
    }

    public RatingSatistics getStats() {
        return rs;
    }
}
