package eu.ase.threads.paralel;

public class MyMultiThreadArray implements Runnable {
    private int[] v;
    private int startIdx;
    private int stopIdx;
    private Long sum;

    public MyMultiThreadArray(int[] v, int startIdx, int stopIdx) {
        this.v = v;
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;

    }

    @Override
    public void run() {
        long s = 0;
        for(int i = startIdx; i <= stopIdx; i++) {
            s += v[i];
        }
        this.sum = s;
    }

    public Long getSum() {
        return this.sum;
    }

}
