package eu.ase.threads.paralel;

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

    }

}
