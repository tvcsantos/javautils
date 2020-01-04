package pt.com.santos.util.encoding;

import pt.com.santos.util.exception.DecoderException;

public interface Decoder<K, T> {
    T decode(K o) throws DecoderException;
}
