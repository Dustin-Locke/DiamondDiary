package locke.dustin.diamonddiary.util.exception;

public class PasswordHashingException extends RuntimeException {

    public PasswordHashingException (
            String message,
            Throwable cause ) {

        super(
                message,
                cause );
    }
}
