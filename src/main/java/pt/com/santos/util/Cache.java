package pt.com.santos.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class Cache<A, B>
        extends HashMap<A, B> {

    public Cache() {
        super();
    }

    public void store(OutputStream os) throws IOException {
        store(this, os);
    }

    public static <A, B> void store(Cache<A, B> cache,
            OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(cache);
        oos.close();
    }

    public static <A, B> Cache<A, B> load(InputStream is) throws
            IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(is);
        return (Cache<A, B>) ois.readObject();
    }
}