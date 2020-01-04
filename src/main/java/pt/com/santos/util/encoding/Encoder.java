package pt.com.santos.util.encoding;

import pt.com.santos.util.exception.EncoderException;

public interface Encoder<T, K> {
    K encode(T o) throws EncoderException;
}
