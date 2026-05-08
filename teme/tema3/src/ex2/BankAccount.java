package ex2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private final List<BankTransaction> transactions;

    public BankAccount() {
        this.balance = 10_000;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void resetBalance() {
        this.balance = 10000;
    }

    public void modifyBalance(BankTransaction transaction) {
        if(transaction.getType() == TransactionType.DEPOSIT)
            this.balance += transaction.getAmount();
        else
            this.balance -= transaction.getAmount();
    }

    public List<BankTransaction> getTransactions() {
        return new ArrayList<>(this.transactions);
    }

    public int getTransactionCount() {
        return this.transactions.size();
    }

    public void readFromCsv(String filename) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = reader.readLine()) != null) {
                this.transactions.add(BankTransaction.fromLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
