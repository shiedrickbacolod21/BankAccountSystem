package bankaccount;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void testDepositValidAmount_WhenValidDepositAmount_ReturnDespositedAmount() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
        assertTrue(outContent.toString().contains("Deposited: Php 1000."));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Deposit with zero amount")
    void testDepositZeroAmount_WhenDepositAmountIsZero_ReturnAmountMustBePositive() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(0);
        assertEquals(0.0, account.getBalance());
        assertTrue(outContent.toString()
                .contains("The deposit amount must be positive."));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Deposit with negative amount")
    void testDepositNegativeAmount_WhenDepositAmountIsNegative_ReturnAmountMustBePositive() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(-500);
        assertEquals(0.0, account.getBalance());
        assertTrue(outContent.toString()
                .contains("The deposit amount must be positive."));
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Withdraw with sufficient funds")
    void testWithdrawSufficientFunds_WhenSuffiecientFunds_ReturnWithdrawnAmount() {
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
    void testWithdrawInsufficientFunds_WhenInsuffiecientFunds_ReturnInsufficientBalance() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        final double depositAmount = 1000;
        final double withdrawAmount = 1500;
        account.deposit(depositAmount);
        outContent.reset();
        account.withdraw(withdrawAmount);
        assertEquals(depositAmount, account.getBalance());
        assertTrue(outContent.toString().contains("Insufficient balance."));
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Withdraw with negative amount")
    void testWithdrawNegativeAmount_WhenWithdrawnAmountIsNegative_ReturnAmountMustBePositive() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        final double depositAmount = 1000;
        final double negativeAmount = -100;
        account.deposit(depositAmount);
        outContent.reset();
        account.withdraw(negativeAmount);
        assertEquals(depositAmount, account.getBalance());
        assertTrue(outContent.toString()
                .contains("The withdrawn amount must be positive."));
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Deposit when account is frozen")
    public void testDepositWhenFrozen_WhenAccountIsFrozen_ReturnAccountFrozenCantDeposit() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.freezeAccount();
        account.deposit(11500);
        String output = outContent.toString();
        assertTrue(output.contains("Account has been frozen."));
        assertTrue(output.contains("Account is frozen. Can't deposit."));
    }
    
    @Test
    @Order(9)
    @DisplayName("Test 9: Withdraw when account is frozen")
    void testWithdrawWhenFrozen_WhenAccountFrozen_ReturnAccountFrozenCantWithdraw() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.freezeAccount();
        outContent.reset();
        account.withdraw(500);
        assertTrue(outContent.toString()
                .contains("Account is frozen. Can't withdraw"));
    }
    
    @Test
    @Order(10)
    @DisplayName("Test 10: Unfreeze account and withdraw")
    void testUnfreezeAccount_WhenAccountUnfrozen_ReturnWithdrawnAmount() {
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
    void testGetBalanceMethod_WhenMultipleTransactions_ReturnCorrectBalance() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");
        account.deposit(1000);
        account.withdraw(500);
        account.withdraw(100);
        assertEquals(400, account.getBalance());
    }
    
    @Test
    @Order(13)
    @DisplayName("Test 13: Withdraw exact balance should leave zero")
    void testWithdrawExactBalance_WhenWithdrawExactAmountAsBalance_ReturnCorrectBalance() {
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
    void testMultipleDeposits_WhenMultipleDeposits_ReturnTotalBalance() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");

        account.deposit(500);
        account.deposit(300);

        assertEquals(800, account.getBalance());
    }
    
    @Test
    @Order(15)
    @DisplayName("Test 15: Deposit after unfreeze should work")
    void testDepositAfterUnfreeze_WhenUnfreezeAccount_ReturnDepositedAmount() {
        SavingsAccount account = new SavingsAccount("Shiedrick Bacolod");

        account.freezeAccount();
        account.unfreezeAccount();
        outContent.reset();

        account.deposit(500);

        assertEquals(500, account.getBalance());
        assertTrue(outContent.toString().contains("Deposited: Php 500."));
    }

    @Test
    @DisplayName("Main Function")
    void testMain_ReturnVoid() {
        SavingsAccount.main(null);
    }
}
