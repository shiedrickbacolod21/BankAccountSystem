package bankaccount;

public class InsufficientFundsException extends Exception {

    /** Serial version UID for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InsufficientFundsException with the specified message.
     *
     * @param msg the detail message
     */
    public InsufficientFundsException(final String msg) {
        super(msg);
    }
}
