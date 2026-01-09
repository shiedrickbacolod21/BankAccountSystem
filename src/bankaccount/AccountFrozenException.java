package bankaccount;

public class AccountFrozenException extends Exception {

    /** Serial version UID for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new AccountFrozenException with the specified message.
     *
     * @param msg the detail message
     */
    public AccountFrozenException(final String msg) {
        super(msg);
    }
}
