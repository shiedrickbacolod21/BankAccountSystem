package bankaccount;

public abstract class AbstractBankAccount implements BankAccount {
    /** The current balance of the account. */
    private double balance;
    /** The frozen status of the account. */
    private boolean isFrozen;
    /** ANSI escape code to reset console text formatting. */
    public static final String ANSI_RESET = "\u001B[0m";
    /** ANSI escape code to set console text color to red. */
    public static final String ANSI_RED = "\u001B[31m";
    /** ANSI escape code to set console text color to green. */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Initializes balance to 0 and isFrozen to false.
     */
    public AbstractBankAccount() {
        this.balance = 0;
        this.isFrozen = false;
    }

    /**
     * Deposits an amount. Validates that the account is not frozen and amount
     * is positive.
     *
     * @param amount the amount to deposit
     */
    public void deposit(final double amount) {
        if (isFrozen) {
            System.out.println(ANSI_RED + "Account is frozen. Can't deposit."
                    + ANSI_RESET);
            return;
        } else if (amount <= 0) {
            System.out.println(ANSI_RED + "The deposit amount must be positive."
                    + ANSI_RESET);
            return;
        } else {
            balance += amount;
            System.out.printf("Deposited: Php %.2f ", amount);
            System.out.println();
        }
    }

    /**
     * Withdraws an amount. Validates that the account is not frozen, amount is
     * positive, and sufficient balance.
     *
     * @param amount
     */
    public void withdraw(final double amount) {
        if (isFrozen) {
            System.out.println(ANSI_RED + "Account is frozen. Can't withdraw."
                    + ANSI_RESET);
            return;
        } else if (amount <= 0) {
            System.out.println(ANSI_RED
                    + "The withdrawn amount must be positive." + ANSI_RESET);
            return;
        } else if (amount > balance) {
            System.out.println(ANSI_RED + "Insufficient balance." + ANSI_RESET);
            return;
        } else {
            balance -= amount;
            System.out.printf("Withdrawn: Php %.2f", amount);
            System.out.println();
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
}
