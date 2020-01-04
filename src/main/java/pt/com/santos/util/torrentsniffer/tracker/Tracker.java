/*
 * Created on Jul 15, 2004
 */
package pt.com.santos.util.torrentsniffer.tracker;

import pt.com.santos.util.torrentsniffer.torrent.TorrentState;

/**
 * Represents the Torrent's Tracker. 
 * 
 * copied and adapted from http://torrentsniffer.sourceforge.net/
 * 
 * @author Larry Williams
 *
 */
public interface Tracker {
    /**
     * @return Returns the url.
     */
    public abstract String getAnnounce();

    /**
     * @param url
     *            The url to set.
     */
    public abstract void setAnnounce(String url);

    /**
     * Tries to retrieve the state of all torrents tracked by this server. Uses
     * scraping to do this. Not all trackers support this.
     * 
     * @return
     */
    public abstract TorrentState[] getTrackerState();

    /**
     * Retrieves the scrape url for the tracker.
     * 
     * Taken from http://groups.yahoo.com/group/BitTorrent/message/3275
     * 
     * Take the tracker url. Find the last '/' in it. If the text immediately
     * following that '/' isn't 'announce' it will be taken as a sign that that
     * tracker doesn't support the scrape convention. If it does, substitute
     * 'scrape' for 'announce' to find the scrape page.
     * 
     * @return
     */
    public abstract String getScrapeUrl();
}