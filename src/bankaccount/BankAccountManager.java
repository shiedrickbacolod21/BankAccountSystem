package bankaccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankAccountManager {

    /** Maps account IDs to bank accounts. */
    private Map<Integer, BankAccount> accounts;
    /** The next available account ID. */
    private int nextAccountId;

    /**
     * Constructs a new BankAccountManager with an empty account list and the
     * next account ID set to 1.
     */
    public BankAccountManager() {
        accounts = new HashMap<>();
        nextAccountId = 1;
    }

    /**
     * Adds a bank account and assigns it a unique account ID.
     *
     * @param account the bank account to add
     */
    public void addAccount(final BankAccount account) {
        accounts.put(nextAccountId++, account);
    }

    /**
     * Retrieves a bank account by its ID.
     *
     * @param id the account ID
     * @return the bank account, or null if not found
     */
    public BankAccount getAccount(final int id) {
        return accounts.get(id);
    }

    /**
     * Lists all bank accounts and their balances to the console.
     */
    public void listAccounts() {
        System.out.println("== Account List ==");
        accounts.forEach((id, account) -> System.out.printf(
                "Bank Account ID: %d | Balance: Php %.2f", id,
                account.getBalance()));
    }

    /**
     * Filters transactions with amounts greater than or equal to the specified
     * amount. Throws InvalidAmountException if the threshold amount is
     * negative.
     *
     * @param amount the threshold amount
     * @param tx     the list of transactions to filter
     * @return a list of transactions with amounts >= amount
     */
    public List<Transaction> filterTransactionsAbove(final double amount,
            final List<Transaction> tx) throws InvalidAmountException {
        // Filter must not accept negative amount.
        // Adjust this function to not include neative
        if (amount < 0) {
            throw new InvalidAmountException(
                    "The amount can't be negative: " + amount);
        }

        return tx.stream()
                .filter(t -> t.getAmount() >= amount && t.getAmount() >= 0)
                .collect(Collectors.toList());
    }

    /**
     * Sorts a list of transactions by their amounts in ascending order.
     *
     * @param tx the list of transactions to sort
     * @return a new list of transactions sorted by amount
     */
    public List<Transaction> sortTransactionsByAmount(
            final List<Transaction> tx) {
        return tx.stream()
                .sorted((a, b) -> Double.compare(a.getAmount(), b.getAmount()))
                .collect(Collectors.toList());
    }
}
