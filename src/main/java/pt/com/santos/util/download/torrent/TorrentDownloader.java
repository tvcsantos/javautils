package pt.com.santos.util.download.torrent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pt.com.santos.util.torrentsniffer.torrent.Torrent;
import pt.com.santos.util.torrentsniffer.torrent.TorrentState;
import pt.com.santos.util.download.Downloader;

public class TorrentDownloader implements Downloader {

    private URL url;
    private String fileName;
    private File saveDir;

    private TorrentDownloader(URL url, String fileName, File saveDir) {
        this.url = url;
        this.fileName = fileName;
        this.saveDir = saveDir;
    }

    public List<File> download() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int length = conn.getContentLength();
        InputStream in = conn.getInputStream();
        String s = conn.getHeaderField("Content-Disposition");
        Matcher m1 = Pattern.compile(".*filename=\"(.*)\"").matcher(s);
        if (m1.matches()) {
            String f = m1.group(1);
            f = f != null ? f.trim() : f;
            int index = -1;
            if (f != null && (index = f.lastIndexOf(".torrent")) != -1)
                f = f.substring(0, index);
            if (fileName == null) fileName = f;
        }
        
        // incredible ugly hack to retrieve torrents from isohunt
        if (length == -1) {
            length = 250000;
        }

        BufferedInputStream bis = new BufferedInputStream(in);
        FileOutputStream bos = new FileOutputStream(
                new File(saveDir,fileName + ".torrent"));

        byte[] buff = new byte[length];
        int bytesRead;

        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }

        in.close();
        bis.close();
        bos.close();

        return Arrays.asList(new File[]{
            new File(saveDir, fileName + ".torrent")});
    }

    public static List<File> download(URL url, String fileName, File saveDir)
            throws IOException {
        return new TorrentDownloader(url, fileName, saveDir).download();
    }

    public static int getSeedersFromTorrent(Torrent torrent) {
        // First try to get the number of the torrent.
        int numberOfSeeders = 0;
        TorrentState torrentState = torrent.getState();
        numberOfSeeders = torrentState.getComplete();
        return numberOfSeeders;
    }
}
