package pt.com.santos.util.exception;

public class NotExecutableException extends Exception {

    public NotExecutableException(Throwable cause) {
        super(cause);
    }

    public NotExecutableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExecutableException(String message) {
        super(message);
    }

    public NotExecutableException() {
        super();
    }

}
