package pt.com.santos.util.download;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Downloader {
    public List<File> download() throws IOException;
}
