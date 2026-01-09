package bankaccount;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankSystemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream origOut = System.out;

    @BeforeEach
    void setup() {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(origOut);
        System.out.println(outContent.toString());
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("=== All tests are done ===");
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Create a savings account")
    void testCreateSavingsAccount_WhenCreatingAnInstanceOfSavingsAccount_ReturnOwnerName() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        assertEquals("Shiedrick Bacolod", account.getOwnerName(),
                "Doesn't match the expected output");
        System.out.println("Owner: " + account.getOwnerName());
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Deposit with valid amount")
    void testDepositValidAmount_WhenValidDepositAmount_ReturnDespositedAmount()
            throws InvalidAmountException, AccountFrozenException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
        assertTrue(outContent.toString().contains("Deposited: Php 1000."));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Deposit with zero amount")
    void testDepositZeroAmount_WhenDepositAmountIsZero_ReturnErrorMessage()
            throws InvalidAmountException, AccountFrozenException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        InvalidAmountException ex = assertThrows(InvalidAmountException.class,
                () -> account.deposit(0));
        assertEquals(0, account.getBalance(), 0.001);
        assertTrue(ex.getMessage().contains("Deposit must be greater than 0"));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Deposit with negative amount")
    void testDepositNegativeAmount_WhenDepositAmountIsNegative_ReturnAmountMustBePositive()
            throws InvalidAmountException, AccountFrozenException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        InvalidAmountException ex = assertThrows(InvalidAmountException.class,
                () -> account.deposit(-500));
        assertEquals(0, account.getBalance(), 0.001);
        assertTrue(ex.getMessage().contains("Deposit must be greater than 0"));
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Withdraw with sufficient funds")
    void testWithdrawSufficientFunds_WhenSuffiecientFunds_ReturnWithdrawnAmount()
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        final double depositAmount = 1000;
        final double withdrawAmount = 500;
        account.deposit(depositAmount);
        outContent.reset();
        account.withdraw(withdrawAmount);
        assertEquals(withdrawAmount, account.getBalance());
        assertTrue(outContent.toString().contains("Withdrawn: Php 500."));
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Withdraw with insufficient funds")
    void testWithdrawInsufficientFunds_WhenInsuffiecientFunds_ReturnInsufficientBalance()
            throws InsufficientFundsException, InvalidAmountException,
            AccountFrozenException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        InsufficientFundsException ex = assertThrows(
                InsufficientFundsException.class, () -> account.withdraw(1500));
        assertEquals(1000, account.getBalance(), 0.001);
        assertTrue(ex.getMessage().contains("Balance not enough"));
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Withdraw with negative amount")
    void testWithdrawNegativeAmount_WhenWithdrawnAmountIsNegative_ReturnAmountMustBePositive()
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        InvalidAmountException ex = assertThrows(
                InvalidAmountException.class, () -> account.withdraw(-100));
        assertEquals(1000, account.getBalance(), 0.001);
        assertTrue(ex.getMessage().contains("Withdrawn amount must be greater than 0"));
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Deposit when account is frozen")
    public void testDepositWhenFrozen_WhenAccountIsFrozen_ReturnAccountFrozenCantDeposit()
            throws AccountFrozenException, InvalidAmountException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.freezeAccount();
        AccountFrozenException ex = assertThrows(
                AccountFrozenException.class, () -> account.deposit(500));
        assertTrue(ex.getMessage().contains("Account is frozen"));
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Withdraw when account is frozen")
    void testWithdrawWhenFrozen_WhenAccountFrozen_ReturnAccountFrozenCantWithdraw()
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.freezeAccount();
        AccountFrozenException ex = assertThrows(
                AccountFrozenException.class, () -> account.withdraw(500));
        assertTrue(ex.getMessage().contains("Account is frozen"));
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Unfreeze account and withdraw")
    void testUnfreezeAccount_WhenAccountUnfrozen_ReturnWithdrawnAmount()
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        account.freezeAccount();
        account.unfreezeAccount();
        outContent.reset();
        account.withdraw(100);
        assertEquals(900, account.getBalance());
        assertTrue(outContent.toString().contains("Withdrawn: Php 100."));
    }

    @Test
    @Order(11)
    @DisplayName("Test 11: Check account is not frozen")
    void testIsFrozen_WhenAccountIsFrozen_ReturnFalse() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        assertFalse(account.isFrozen());
    }

    @Test
    @Order(12)
    @DisplayName("Test 12: Check balance after multiple transactions")
    void testGetBalanceMethod_WhenMultipleTransactions_ReturnCorrectBalance()
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        account.withdraw(500);
        account.withdraw(100);
        assertEquals(400, account.getBalance());
    }

    @Test
    @Order(13)
    @DisplayName("Test 13: Withdraw exact balance should leave zero")
    void testWithdrawExactBalance_WhenWithdrawExactAmountAsBalance_ReturnCorrectBalance()
            throws InvalidAmountException, AccountFrozenException,
            InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        outContent.reset();

        account.withdraw(1000);

        assertEquals(0, account.getBalance());
        assertTrue(outContent.toString().contains("Withdrawn: Php 1000."));
    }

    @Test
    @Order(14)
    @DisplayName("Test 14: Multiple deposits should accumulate")
    void testMultipleDeposits_WhenMultipleDeposits_ReturnTotalBalance()
            throws InvalidAmountException, AccountFrozenException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");

        account.deposit(500);
        account.deposit(300);

        assertEquals(800, account.getBalance());
    }

    @Test
    @Order(15)
    @DisplayName("Test 15: Deposit after unfreeze should work")
    void testDepositAfterUnfreeze_WhenUnfreezeAccount_ReturnDepositedAmount()
            throws InvalidAmountException, AccountFrozenException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");

        account.freezeAccount();
        account.unfreezeAccount();
        outContent.reset();

        account.deposit(500);

        assertEquals(500, account.getBalance());
        assertTrue(outContent.toString().contains("Deposited: Php 500."));
    }

    @Test
    @Order(16)
    @DisplayName("Test 16: Transaction history and filter")
    void testTransactionHistoryAndFilter_WhenFilterTransactionsAboveThreshold_ReturnsExpected() throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        BankAccountManager manager = new BankAccountManager();
        account.deposit(1000);
        account.deposit(500);
        account.withdraw(200);

        List<Transaction> history = account.getTransactionHistory();
        assertEquals(3, history.size());

        List<Transaction> filtered = manager.filterTransactionsAbove(500,
                history);
        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().anyMatch(t -> t.getAmount() == 1000));
        assertTrue(filtered.stream().anyMatch(t -> t.getAmount() == 500));
    }

    @Test
    @Order(17)
    @DisplayName("Test 17: Sort transactions by amount")
    void testSortTransactions_WhenSortByAmount_ReturnsAscendingOrder() throws InvalidAmountException,
            AccountFrozenException, InsufficientFundsException {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        BankAccountManager manager = new BankAccountManager();
        account.deposit(1000);
        account.deposit(500);
        account.withdraw(200);

        List<Transaction> sorted = manager
                .sortTransactionsByAmount(account.getTransactionHistory());
        assertEquals(3, sorted.size());
        assertEquals(200, sorted.get(0).getAmount());
        assertEquals(500, sorted.get(1).getAmount());
        assertEquals(1000, sorted.get(2).getAmount());
    }

    @Test
    @Order(18)
    @DisplayName("Test 18: BankAccountManager get account")
    void testBankAccountManagerGetAccount_WhenAddingBankAccount_ReturnsCorrectAccount() {
        BankAccountManager manager = new BankAccountManager();
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        manager.addAccount(account);
        assertNotNull(manager.getAccount(1));
        assertEquals(account, manager.getAccount(1));
    }

    @Test
    @DisplayName("Main Function")
    void testMain_ReturnVoid() {
        SavingsAccount.main(null);
    }
}
