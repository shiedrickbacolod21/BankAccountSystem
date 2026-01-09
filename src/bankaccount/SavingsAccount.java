package bankaccount;

import java.util.List;

public class SavingsAccount extends AbstractBankAccount {
    /** The name of the account owner. */
    private String ownerName;

    /**
     * Constructs a new SavingsAccount with the given owner name.
     *
     * @param oOwnerName the name of the account owner
     */
    public SavingsAccount(final String oOwnerName) {
        this.ownerName = oOwnerName;
    }

    /**
     * Gets the owner name of the account.
     *
     * @return the owner name
     */
    public final String getOwnerName() {
        return ownerName;
    }

    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws Exception;
    }

    /**
     * Utility method to run an action with a separator line.
     *
     * @param action the Runnable action to execute
     */
    private static void run(final ThrowingRunnable action) {
        System.out.println("---------------------------");
        try {
            action.run();
        } catch (Exception e) {
            System.out.println(
                    e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    /**
     * The main entry point of the Bank Account System.
     *
     * @param args Main Function.
     */
    public static void main(final String[] args) {

        final double deposit1000 = 1000.00;
        final double deposit0 = 0.00;
        final double depositNegative = -500.00;
        final double withdraw100 = 100.00;
        final double withdraw500 = 500.00;
        final double withdraw1500 = 1500.00;
        final double withdrawNegative = -100.00;
        final double deposit500 = 500.00;
        final double filterAmount = 500.00;
        final int accountId = 100;

        System.out.println("=== BANK ACCOUNT SYSTEM ===");
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod\n");
        System.out.println("Owner: " + account.getOwnerName());

        manager.addAccount(account);
        manager.listAccounts();
        System.out.println();

        run(() -> account.deposit(deposit1000));
        run(() -> account.deposit(deposit0));
        run(() -> account.deposit(depositNegative));

        System.out.println("---------------------------");
        System.out.printf("Current Balance: Php %.2f\n", account.getBalance());

        run(() -> account.withdraw(withdraw500));
        run(() -> account.withdraw(withdraw1500));
        run(() -> account.withdraw(withdrawNegative));

        System.out.println("--------------------------");
        account.freezeAccount();
        run(() -> account.deposit(deposit500));
        run(() -> account.withdraw(withdraw500));
        System.out.println("--------------------------");
        account.unfreezeAccount();
        run(() -> account.withdraw(withdraw100));
        System.out.println("--------------------------");

        System.out.printf("New Balance: Php %.2f\n", account.getBalance());
        System.out.println("--------------------------");

        List<Transaction> history = account.getTransactionHistory();
        try {
            List<Transaction> filtered = manager
                    .filterTransactionsAbove(filterAmount, history);
            System.out.println(
                    "Filtered Transactions:");
            filtered.forEach(System.out::println);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------");
        List<Transaction> sorted = manager.sortTransactionsByAmount(history);
        System.out.println("Sorted Transactions:");
        sorted.forEach(System.out::println);

        run(() -> {
            manager.getAccount(accountId);
            System.out.println("Exception: NullPointerException");
        });
    }

}
