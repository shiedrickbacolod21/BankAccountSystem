package bankaccount;

public class SavingsAccount extends AbstractBankAccount {
    /**
     * The name of the account owner.
     */
    private String ownerName;

    /**
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

    /**
     * The main entry point of the Bank Account System.
     *
     * @param args Main Function.
     */
    public static void main(final String[] args) {
        final double deposit1000 = 1000.00;
        final double deposit0 = 0.00;
        final double depositNegative = -500.00;
        final double withdraw500 = 500.00;
        final double withdraw1500 = 1500.00;
        final double withdrawNegative = -500.00;
        final double deposit11500 = 11500.00;
        final double withdraw100 = 100.00;

        System.out.println("=== BANK ACCOUNT SYSTEM ===");
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        System.out.println("Owner: " + account.getOwnerName());
        account.deposit(deposit1000);
        System.out.printf("Deposited amount: Php %.2f || ", deposit0);
        account.deposit(deposit0);
        System.out.printf("Deposited amount: Php %.2f || ", depositNegative);
        account.deposit(depositNegative);
        System.out.printf("Current Balance: Php %.2f ", account.getBalance());
        account.withdraw(withdraw500);
        System.out.printf("New Balance: Php %.2f ", account.getBalance());
        System.out.printf("Withdrawn amount: Php %.2f || ", withdraw1500);
        account.withdraw(withdraw1500);
        System.out.printf("Withdrawn amount: Php %.2f || ", withdrawNegative);
        account.withdraw(withdrawNegative);
        account.freezeAccount();
        account.deposit(deposit11500);
        account.withdraw(withdraw500);
        account.unfreezeAccount();
        account.withdraw(withdraw100);
        System.out.print("Account frozen: ");
        System.out.println(account.isFrozen());
        System.out.printf("Total Balance: Php %.2f ", account.getBalance());
    }
}
