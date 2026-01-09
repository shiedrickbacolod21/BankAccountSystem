package bankaccount;

import java.util.List;

public interface BankAccount {
    /**
     * Deposits an amount into the account.
     *
     * @param amount the amount to deposit
     */
    void deposit(double amount)
            throws InvalidAmountException, AccountFrozenException;

    /**
     * Withdraws an amount from the account.
     *
     * @param amount the amount to withdraw
     */
    void withdraw(double amount) throws InvalidAmountException,
            InsufficientFundsException, AccountFrozenException;

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

    /**
     * Freezes the account, preventing deposits and withdrawals.
     */
    void freezeAccount();

    /**
     * Unfreezes the account, allowing deposits and withdrawals.
     */
    void unfreezeAccount();

    /**
     * Gets the transaction history of the account.
     *
     * @return a list of transactions
     */
    List<Transaction> getTransactionHistory();
}
