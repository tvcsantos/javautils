package pt.com.santos.util.subtitle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public interface SubtitleDescriptor extends Serializable {

    String getName();

    String getLanguageName();

    String getType();

    String getSubFileName();

    void setSubFileName(String subFileName);

    void saveContent(File f) throws IOException;

    String getSubHash();

    void setSubHash(String hash);

    String getISO639();

    void setISO639(String iso639);

    String getSubLink();

    void setSubLink(String subLink);

    String getSubDownloadLink();

    void setSubDownloadLink(String subDownloadLink);

    String getData();

    void setData(String data);

    String getDownFileName();

    void setDownFileName(String downFileName);
    
    String getContent() throws IOException;
}
