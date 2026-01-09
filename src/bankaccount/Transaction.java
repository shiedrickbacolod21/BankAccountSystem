package bankaccount;

public class Transaction {
    /** The type of transaction (e.g., "Deposit" or "Withdraw"). */
    private String type;
    /** The amount involved in the transaction. */
    private double amount;

    /**
     * Constructs a new Transaction with the specified type and amount.
     *
     * @param tType   the type of transaction
     * @param aAmount the amount of the transaction
     */
    public Transaction(final String tType, final double aAmount) {
        this.type = tType;
        this.amount = aAmount;
    }

    /**
     * Gets the transaction type.
     *
     * @return the type of the transaction
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    @Override
    public final String toString() {
        return type + ": Php " + String.format("%.2f", amount);
    }
}
