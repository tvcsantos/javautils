/*
 * Created on Jul 11, 2004
 */
package pt.com.santos.util.torrentsniffer.torrent;

/**
 * Thrown if an error occurs while processing the Torrent.
 * 
 * copied and adapted from http://torrentsniffer.sourceforge.net/
 * 
 * @author Larry Williams
 *  
 */
public class TorrentException extends RuntimeException {
    /**
     * 
     * @param message
     * @param exception
     */
    public TorrentException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     * @param ex
     */
    public TorrentException(Throwable ex) {
        super(ex);
    }

    /**
     * @param string
     */
    public TorrentException(String message) {
        super(message);
    }
}