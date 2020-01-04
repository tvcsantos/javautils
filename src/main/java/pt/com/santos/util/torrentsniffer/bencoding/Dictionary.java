/*
 * Created on Jul 11, 2004
 */
package pt.com.santos.util.torrentsniffer.bencoding;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of the bencoding Dictionary type.
 * 
 * copied and adapted from http://torrentsniffer.sourceforge.net/
 * 
 * @author Larry Williams
 *  
 */
public class Dictionary {

    private Object keyPair[];

    private Map dictionary = new TreeMap();

    /**
     * Adds a value to the Dictionary.
     * 
     * @param value
     * @throws UnsupportedEncodingException
     */
    public void addValue(Object value) {
        if (keyPair == null) {
            keyPair = new Object[2];
        }

        if (keyPair[0] == null && keyPair[1] == null) {
            // Check that key is a String
            if (value instanceof byte[]) {
                try {
                    keyPair[0] = new String((byte[]) value,
                            BencodingImpl.BYTE_ENCODING);
                } catch (UnsupportedEncodingException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                throw new BencodingException(
                        "Dictionary key must be a byte[] (String). Not "
                                + value.getClass());
            }
        } else if (keyPair[0] != null && keyPair[1] == null) {
            keyPair[1] = value;
            dictionary.put(keyPair[0], keyPair[1]);
            keyPair = null;
        } else {
            throw new IllegalStateException(
                    "Key is not set but value is set to '" + keyPair[1] + "'");
        }
    }

    /**
     * Retrieve the value from the dictionary by specifying a key.
     * 
     * @param key
     * @return
     */
    public Object get(String key) {
    	return dictionary.get(key);
    }

    /**
     * Retrieves a String from the Dictionary
     * 
     * @param key
     * @return
     */
    public String getString(String key) {
    	byte value[];
    	try
       {
    	  value = (byte[]) dictionary.get(key);
       }
       catch (ClassCastException e)
       {
    	   value = null;
       }
        String string = null;

        if (value != null) {
            try {
                string = new String(value, BencodingImpl.BYTE_ENCODING);
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }
        }
        return string;
    }

    /**
     * Check that all keys have an associated value.
     *  
     */
    public void close() {
        if (keyPair != null) {
            throw new BencodingException(
                    "Each entry in the sDictionary should have a key and a value.");
        }
    }

    /**
     * Returns the size of the dictionary.
     * 
     * @return
     */
    public int size() {
        return dictionary.size();
    }

    /**
     * Return the keys in this dictionary.
     * 
     * @return
     */
    public String[] getKeys() {
        return (String[]) dictionary.keySet().toArray(
                new String[dictionary.size()]);
    }
}