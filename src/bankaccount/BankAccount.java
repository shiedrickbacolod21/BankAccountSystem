package bankaccount;

public interface BankAccount {
    /**
     * Deposits an amount into the account.
     *
     * @param amount the amount to deposit
     */
    void deposit(double amount);

    /**
     * Withdraws an amount from the account.
     *
     * @param amount the amount to withdraw
     */
    void withdraw(double amount);

    /**
     * Gets the current balance of the account.
     *
     * @return the current balance
     */
    double getBalance();

    /**
     * Checks if the account is frozen.
     *
     * @return true if frozen, false if otherwise
     */
    boolean isFrozen();
}
