package pt.com.santos.util.exception;

public class UnsupportedOperatingSystemException extends Exception {

    public UnsupportedOperatingSystemException(Throwable cause) {
        super(cause);
    }

    public UnsupportedOperatingSystemException(
            String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedOperatingSystemException(String message) {
        super(message);
    }

    public UnsupportedOperatingSystemException() {
        super();
    }

}
