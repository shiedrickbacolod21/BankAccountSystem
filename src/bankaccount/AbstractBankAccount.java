package bankaccount;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBankAccount implements BankAccount {
    /** The current balance of the account. */
    private double balance;
    /** The frozen status of the account. */
    private boolean isFrozen;

    /** List of transactions performed on this account. */
    private List<Transaction> transactionHistory;

    /** ANSI escape code to reset console text formatting. */
    public static final String ANSI_RESET = "\u001B[0m";
    /** ANSI escape code to set console text color to red. */
    public static final String ANSI_RED = "\u001B[31m";
    /** ANSI escape code to set console text color to green. */
    public static final String ANSI_GREEN = "\u001B[32m";
    /** ANSI escape code to set console text color to yellow. */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * Initializes balance to 0 and isFrozen to false.
     */
    public AbstractBankAccount() {
        this.balance = 0;
        this.isFrozen = false;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Deposits an amount. Validates that the account is not frozen and amount
     * is positive.
     *
     * @param amount the amount to deposit
     */
    @Override
    public synchronized void deposit(final double amount)
            throws InvalidAmountException, AccountFrozenException {
        if (isFrozen) {
            System.out.println(ANSI_RED + "Can't deposit."
                    + ANSI_RESET);
            throw new AccountFrozenException(
                    "Account is frozen.");
        } else if (amount <= 0) {
            System.out.println(ANSI_RED + "Invalid deposit amount: "
                    + String.format("%.2f", amount) + ANSI_RESET);
            throw new InvalidAmountException(
                    "Deposit must be greater than 0.");
        } else {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println(ANSI_GREEN + "Deposited: Php "
                    + String.format("%.2f", amount) + ANSI_RESET);
        }
    }

    /**
     * Withdraws an amount. Validates that the account is not frozen, amount is
     * positive, and sufficient balance.
     *
     * @param amount
     */
    public synchronized void withdraw(final double amount)
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        if (isFrozen) {
            System.out.println(ANSI_RED + "Can't withdraw."
                    + ANSI_RESET);
            throw new AccountFrozenException(
                    "Account is frozen.");
        } else if (amount <= 0) {
            System.out.println(ANSI_RED + "Invalid withdrawn amount: "
                    + String.format("%.2f", amount) + ANSI_RESET);
            throw new InvalidAmountException(
                    "Withdrawn amount must be greater than 0.");
        } else if (amount > balance) {
            System.out.println(ANSI_RED + "Insufficient balance. Withdrawn: "
                    + String.format("%.2f", amount) + ANSI_RESET);
            throw new InsufficientFundsException(
                    "Balance not enough.");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
            System.out.println(ANSI_YELLOW + "Withdrawn: Php "
                    + String.format("%.2f", amount) + ANSI_RESET);
        }
    }

    /**
     * Gets the current balance.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Checks if the account is frozen.
     *
     * @return true if frozen, false otherwise
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * Freezes the account, preventing deposits and withdrawals.
     */
    public void freezeAccount() {
        isFrozen = true;
        System.out.println("Account has been frozen.");
    }

    /**
     * UnFreezes the account, allowing deposits and withdrawals.
     */
    public void unfreezeAccount() {
        isFrozen = false;
        System.out.println(
                ANSI_GREEN + "Account has been unfrozen." + ANSI_RESET);
    }

    @Override
    public final List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
