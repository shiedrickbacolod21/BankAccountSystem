package bankaccount;

public class InvalidAmountException extends Exception {

    /** Serial version UID for serialization. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidAmountException with the specified message.
     *
     * @param msg the detail message
     */
    public InvalidAmountException(final String msg) {
        super(msg);
    }
}
