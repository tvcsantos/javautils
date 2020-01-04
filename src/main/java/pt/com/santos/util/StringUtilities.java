package pt.com.santos.util;

import java.util.Iterator;

/**
 * copied from net.sourceforge.tuned
 */
public final class StringUtilities {

    public static String join(Object[] values, CharSequence delimiter) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);

            if (i < values.length - 1) {
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }

    public static String join(Iterable<?> values, CharSequence delimiter) {
        StringBuilder sb = new StringBuilder();

        for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
            sb.append(iterator.next());

            if (iterator.hasNext()) {
                sb.append(delimiter);
            }
        }

        return sb.toString();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Dummy constructor to prevent instantiation.
     */
    private StringUtilities() {
        throw new UnsupportedOperationException();
    }
    
    public static String removeFromEnd(String s, String rem) {
        if (s.endsWith(rem)) {
            int i = s.lastIndexOf(rem);
            return s.substring(0, i);
        }
        return s;
    }

    public static String removeFromStart(String s, String rem) {
        if (s.startsWith(rem)) {
            int i = s.indexOf(rem);
            return s.substring(i + rem.length(), s.length());
        }
        return s;
    }
    
    /*public static String removeFromStartArr(String s, String[] rems) {
        for (String rem : rems) s = StringUtilities.removeFromStart(s, rem);
        return s;
    }*/
    
    public static String replace(String original, CharSequence[] targets,
            CharSequence[] replacements) {
        if (original == null) return null;
        if (targets == null || replacements == null)
            throw new NullPointerException();
        if (targets.length != replacements.length)
            throw new RuntimeException();
        if (original.length() <= 0) return original;
        String res = original;
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] == null || replacements[i] == null)
                throw new NullPointerException();
            res = res.replace(targets[i], replacements[i]);
        }
        return res;
    }

    public static String replaceFirst(String original,
            String[] regexs, String[] replacements) {
        if (original == null) return null;
        if (regexs == null || replacements == null)
            throw new NullPointerException();
        if (regexs.length != replacements.length)
            throw new RuntimeException();
        String res = original;
        for (int i = 0; i < regexs.length; i++) {
            if (regexs[i] == null || replacements[i] == null)
                throw new NullPointerException();
            res = res.replaceFirst(regexs[i], replacements[i]);
        }
        return res;
    }

    public static String replaceAll(String original,
            String[] regexs, String[] replacements) {
        if (original == null) return null;
        if (regexs == null || replacements == null)
            throw new NullPointerException();
        if (regexs.length != replacements.length)
            throw new RuntimeException();
        String res = original;
        for (int i = 0; i < regexs.length; i++) {
            if (regexs[i] == null || replacements[i] == null)
                throw new NullPointerException();
            res = res.replaceAll(regexs[i], replacements[i]);
        }
        return res;
    }

    public static String lead(String s) {
        return s == null ? null : s.replaceAll("^\\s+", "");
    }

    public static String trail(String s) {
        return s == null ? null : s.replaceAll("\\s+$", "");
    }
}
