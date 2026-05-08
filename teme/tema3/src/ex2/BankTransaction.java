package ex2;

enum TransactionType {
    DEPOSIT,
    WITHDRAW
}

public class BankTransaction {
    private final String transactionId;
    private final TransactionType type;
    private final double amount;

    static public BankTransaction fromLine(String line) {
        String[] data = line.split(";");
        String transactionId = data[0];
        TransactionType type = TransactionType.valueOf(data[1]);
        double amount = Double.parseDouble(data[2]);

        return new BankTransaction(transactionId, type, amount);
    }

    static public BankTransaction of(String transactionId, TransactionType type, double amount) {
        return new BankTransaction(transactionId, type, amount);
    }

    private BankTransaction(String transactionId, TransactionType type, double amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "transactionId=" + transactionId +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
