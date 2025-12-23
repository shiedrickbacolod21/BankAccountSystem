package bankaccount;

public abstract class AbstractBankAccount implements BankAccount {
    /** The current balance of the account. */
    private double balance;
    /** The frozen status of the account. */
    private boolean isFrozen;

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
            System.err.println("Account is frozen. Can't deposit.");
            return;
        } else if (amount <= 0) {
            System.err.println("The deposit amount must be positive.");
            return;
        } else {
            balance += amount;
            System.out.println("Deposited: Php " + (int) amount + ".");
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
            System.err.println("Account is frozen. Can't withdraw.");
            return;
        } else if (amount <= 0) {
            System.err.println("The withdrawn amount must be positive.");
            return;
        } else if (amount > balance) {
            System.err.println("Insufficient balance.");
            return;
        } else {
            balance -= amount;
            System.out.println("Withdrawn: Php " + (int) amount + ".");
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
        System.err.println("Account has been frozen.");
    }

    /**
     * Unfreezes the account, allowing deposits and withdrawals.
     */
    public void unfreezeAccount() {
        isFrozen = false;
        System.out.println("Account has been unfrozen.");
    }
}
