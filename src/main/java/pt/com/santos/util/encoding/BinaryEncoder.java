package pt.com.santos.util.encoding;

import pt.com.santos.util.exception.EncoderException;

public interface BinaryEncoder extends 
        Encoder<byte[], byte[]> {
    String encodeToString(byte[] o) throws EncoderException;
}
