/*
 * Created on Jul 15, 2004
 */
package pt.com.santos.util.torrentsniffer.hash;

/**
 * Interface for classes that can encode data with the SHA-1 algorithm.
 * 
 * copied and adapted from http://torrentsniffer.sourceforge.net/
 * 
 * @author Larry Williams
 *  
 */
public interface SHA1 {
    /**
     * Hash data using the SHA1Impl algorithm.
     * 
     * @param data
     * @return
     */
    public abstract byte[] digest(byte[] data);
}