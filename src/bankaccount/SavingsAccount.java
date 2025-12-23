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
        final int deposit1000 = 1000;
        final int deposit0 = 0;
        final int depositNegative = -500;
        final int withdraw500 = 500;
        final int withdraw1500 = 1500;
        final int withdrawNegative = -500;
        final int deposit11500 = 11500;
        final int withdraw100 = 100;

        System.out.println("Bank Account System");
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        System.out.println("Owner: " + account.getOwnerName());
        account.deposit(deposit1000);
        account.deposit(deposit0);
        account.deposit(depositNegative);
        account.withdraw(withdraw500);
        account.withdraw(withdraw1500);
        account.withdraw(withdrawNegative);
        account.freezeAccount();
        account.deposit(deposit11500);
        account.withdraw(withdraw500);
        account.unfreezeAccount();
        account.withdraw(withdraw100);
        System.out.println(account.isFrozen());
        System.out.println("Balance: Php " + (int) account.getBalance() + ".");
    }
}
