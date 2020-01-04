package pt.com.santos.util.exception;

public class UnsupportedLanguageException extends Exception {

    public UnsupportedLanguageException(Throwable cause) {
        super(cause);
    }

    public UnsupportedLanguageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedLanguageException(String message) {
        super(message);
    }

    public UnsupportedLanguageException() {
    }

}
