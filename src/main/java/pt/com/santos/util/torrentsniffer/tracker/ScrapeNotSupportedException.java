/*
 * Created on Jul 12, 2004
 */
package pt.com.santos.util.torrentsniffer.tracker;

/**
 * Thrown if the server does not support the Tracker Scrape Convention.
 * See http://groups.yahoo.com/group/BitTorrent/message/3275
 * 
 * copied and adapted from http://torrentsniffer.sourceforge.net/
 * 
 * @author Larry Williams
 *  
 */
public class ScrapeNotSupportedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2990686629538163770L;

	/**
     * @param arg0
     */
    public ScrapeNotSupportedException(String message) {
        super(message);
    }
}