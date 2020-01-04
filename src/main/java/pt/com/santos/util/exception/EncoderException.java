package pt.com.santos.util.exception;

public class EncoderException extends Exception {

    public EncoderException(String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EncoderException(Throwable cause) {
        super(cause);
    }

    public EncoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncoderException(String message) {
        super(message);
    }

    public EncoderException() {
    }
    
}
