package ex2;

import java.util.List;

public class BankTransactionTaskSynchronized implements Runnable {

    private final List<BankTransaction> transactions;
    private final int startIdx;
    private final int stopIdx;
    private final BankAccount bankAccount;

    BankTransactionTaskSynchronized(BankAccount bankAccount, int startIdx, int stopIdx) {
        this.transactions = bankAccount.getTransactions();
        this.startIdx = startIdx;
        this.stopIdx = stopIdx;
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for(int i = startIdx; i <= stopIdx; i++)
            synchronized (this.bankAccount) {
                this.bankAccount.modifyBalance(this.transactions.get(i));
            }
    }
}
