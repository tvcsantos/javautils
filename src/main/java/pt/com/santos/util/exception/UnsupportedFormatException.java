package pt.com.santos.util.exception;

public class UnsupportedFormatException extends Exception {

    public UnsupportedFormatException(Throwable cause) {
        super(cause);
    }

    public UnsupportedFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedFormatException(String message) {
        super(message);
    }

    public UnsupportedFormatException() {
    }
    
}
