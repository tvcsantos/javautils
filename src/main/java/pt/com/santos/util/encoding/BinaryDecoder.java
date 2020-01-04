package pt.com.santos.util.encoding;

import pt.com.santos.util.exception.DecoderException;

public interface BinaryDecoder extends Decoder<byte[], byte[]> {
    byte[] decodeFromString(String o) throws DecoderException;
}
