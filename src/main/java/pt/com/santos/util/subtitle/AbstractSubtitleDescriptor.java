package pt.com.santos.util.subtitle;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import pt.com.santos.util.FileUtilities;
import pt.com.santos.util.encoding.BinaryDecoder;
import pt.com.santos.util.exception.DecoderException;

public abstract class AbstractSubtitleDescriptor
        implements SubtitleDescriptor {
    protected BinaryDecoder decoder;

    public AbstractSubtitleDescriptor(BinaryDecoder decoder) {
        this.decoder = decoder;
    }
        
    public final void saveContent(File file) throws IOException {
        byte[] bytes = getBytesFromContent();
        saveDataBytes(file, bytes);
    }
    
    protected byte[] getBytesFromContent() throws IOException {
        try {
            return decoder.decodeFromString(getData());
        } catch (DecoderException ex) {
            throw new IOException(ex);
        }
    }
    
    public String getContent() throws IOException {
        try {
            byte[] bytes = decoder.decodeFromString(getData());
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            return FileUtilities.getContent(bis);
        } catch (DecoderException ex) {
            throw new IOException(ex);
        }
    }
    
    protected void saveDataBytes(File file, byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        int b = -1;
        FileOutputStream fos = new FileOutputStream(file);
        while ((b = bis.read()) != -1) {
            fos.write(b);
        }
        fos.flush();
        fos.close();
        bis.close();
    }
}
