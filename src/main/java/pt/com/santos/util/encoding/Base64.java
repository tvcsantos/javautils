package pt.com.santos.util.encoding;

/**
 * copied and adapted from 
 * https://en.wikibooks.org/wiki/Algorithm_Implementation/Miscellaneous/Base64
 * 
 */
public class Base64 {

    private final static String base64chars = 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String encode(byte[] bytes) {
        return encode(new String(bytes));
    }
    
    public static String encode(String s) {
        StringBuilder sBuilder = new StringBuilder(s);
        // the result/encoded string, the padding string, and the pad count
        StringBuilder r = new StringBuilder();
        StringBuilder p = new StringBuilder();
        int c = sBuilder.length() % 3;

        // add a right zero pad to make this string a multiple of 3 characters
        if (c > 0) {
            for (; c < 3; c++) {
                p.append("=");
                sBuilder.append("\0");
            }
        }

        // increment over the length of the string, three characters at a time
        for (c = 0; c < sBuilder.length(); c += 3) {

            // we add newlines after every 76 output characters, according to
            // the MIME specs
            if (c > 0 && (c / 3 * 4) % 76 == 0) {
                r.append("\r\n");
            }

            // these three 8-bit (ASCII) characters become one 24-bit number
            int n = (sBuilder.charAt(c) << 16) + 
                    (sBuilder.charAt(c + 1) << 8)
                    + (sBuilder.charAt(c + 2));

            // this 24-bit number gets separated into four 6-bit numbers
            int n1 = (n >> 18) & 63, n2 = (n >> 12) & 
                    63, n3 = (n >> 6) & 63, n4 = n & 63;

            // those four 6-bit numbers are used as indices into the base64
            // character list
            r.append(base64chars.charAt(n1)).
                    append(base64chars.charAt(n2)).
                    append(base64chars.charAt(n3)).
                    append(base64chars.charAt(n4));
        }
        
        //return r.substring(0, r.length() - p.length()) + p;
        
        int start = r.length() - p.length();
        int end = r.length();
        if (start <= end) {
            r.delete(start, end);
        }
        return r.append(p).toString();
    }
    
    public static String decode(String str) {

	// remove/ignore any characters not in the base64 characters list
	// or the pad character -- particularly newlines
	str = str.replaceAll("[^" + base64chars + "=]", "");
        StringBuilder s = new StringBuilder(str);

	// replace any incoming padding with a zero pad (the 'A' character is
	// zero)
	String p = (s.charAt(s.length() - 1) == '=' ? 
		(s.charAt(s.length() - 2) == '=' ? "AA" : "A") : "");
	StringBuilder r = new StringBuilder();
        //s = s.substring(0, s.length() - p.length()) + p;
        int start = s.length() - p.length();
        int end = s.length();
        if (start <= end) {
            s.delete(start, end);
        }
        s.append(p);

	// increment over the length of this encoded string, four characters
	// at a time
	for (int c = 0; c < s.length(); c += 4) {

	    // each of these four characters represents a 6-bit index in the
	    // base64 characters list which, when concatenated, will give the
	    // 24-bit number for the original 3 characters
	    int n = (base64chars.indexOf(s.charAt(c)) << 18)
		    + (base64chars.indexOf(s.charAt(c + 1)) << 12)
		    + (base64chars.indexOf(s.charAt(c + 2)) << 6)
		    + base64chars.indexOf(s.charAt(c + 3));

	    // split the 24-bit number into the original three 8-bit (ASCII)
	    // characters
	    r.append((char) ((n >>> 16) & 0xFF)).
                    append((char) ((n >>> 8) & 0xFF)).
                    append((char) (n & 0xFF));
	}
        
        // remove any zero pad that was added to make this a multiple of 24 bits
	//return r.substring(0, r.length() - p.length());
        
        start = r.length() - p.length();
        end = r.length();
        if (start <= end) {
            r.delete(start, end);
        }
        return r.toString();
    }
    
    public static String decode(byte[] bytes) {
        return decode(new String(bytes));
    }
}
