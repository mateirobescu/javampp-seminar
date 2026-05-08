package ex2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static void main(String[] args) {
        BankAccount acc = new BankAccount();
        acc.readFromCsv("transactions.csv");
        List<BankTransaction> transactions = acc.getTransactions();
        for(BankTransaction t : transactions)
            acc.modifyBalance(t);
        System.out.println("Balance Sequential: " + acc.getBalance());
        acc.resetBalance();

        // Varianta fara sincronizare poate da rezultate gresite deoarece doua
        // threaduri pot accesa balanta simultan si sa se piarda date
        final int NUM_OF_THREADS = 4;
        try(ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS)) {
            int transactionsCount = acc.getTransactionCount();
            for(int i = 0; i < NUM_OF_THREADS; i++) {
                int startIdx = (transactionsCount / NUM_OF_THREADS) * i;
                int stopIdx = (transactionsCount / NUM_OF_THREADS) * (i + 1) - 1;
                executorService.execute(new BankTransactionTask(acc, startIdx, stopIdx));
            }

            executorService.shutdown();
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Balance MultiThreading Non-Sync: " + acc.getBalance());
        acc.resetBalance();

        // Synchronized aplica lock pe obiectul respectiv si niciun un alt thread
        // nu poate intra intr-un block synchronized al acelui obiect care are deja lock
        try(ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS)) {
            int transactionsCount = acc.getTransactionCount();
            for(int i = 0; i < NUM_OF_THREADS; i++) {
                int startIdx = (transactionsCount / NUM_OF_THREADS) * i;
                int stopIdx = (transactionsCount / NUM_OF_THREADS) * (i + 1) - 1;
                executorService.execute(new BankTransactionTaskSynchronized(acc, startIdx, stopIdx));
            }

            executorService.shutdown();
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Balance MultiThreading Sync: " + acc.getBalance());

    }
}
