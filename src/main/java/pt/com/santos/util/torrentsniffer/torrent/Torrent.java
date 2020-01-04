/*
 * Created on Jul 5, 2004
 */
package pt.com.santos.util.torrentsniffer.torrent;

import java.util.Date;

import pt.com.santos.util.torrentsniffer.bencoding.Dictionary;
import pt.com.santos.util.torrentsniffer.tracker.Tracker;

/**
 * Represents a Torrent file. Can be used for retrieving information
 * about the Torrent file itself. All data in a Torrent file is bencoded.
 * 
 * copied and adapted from http://torrentsniffer.sourceforge.net/
 * 
 * @author Larry Williams
 *  
 */
public interface Torrent {

    /**
     * Get the tracker.
     * 
     * @return
     */
    Tracker getTracker();

    /**
     * Get the comment.
     * 
     * @return
     */
    String getComment();

    /**
     * Get the creation date.
     * 
     * @return
     */
    Date getCreationDate();

    /**
     * Get the creator.
     * 
     * @return
     */
    String getCreatedBy();

    /**
     * Get the torrent info.
     * 
     * @return
     */
    TorrentInfo getInfo();

    /**
     * Retrieve the state of this torrent.
     * 
     * @return
     */
    TorrentState getState();

    /**
     * Returns the Java version of the bencoded torrent.
     * 
     * @return
     */
    Dictionary getRoot();

}