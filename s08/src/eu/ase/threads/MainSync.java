package eu.ase.threads;

public class MainSync {

    static void main() {
//        ThreadNonSync t1 = new ThreadNonSync("t1");
//        ThreadNonSync t2 = new ThreadNonSync("t2");
        ThreadSync t1 = new ThreadSync("t1");
        ThreadSync t2 = new ThreadSync("t2");
        t1.start();
        t2.start();
        System.out.println("Main program finished");
    }
}
