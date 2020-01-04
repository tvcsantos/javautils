package pt.com.santos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Persistent {

    private Persistent() {
    }

    public static void persist(Serializable obj, File file)
            throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file));
        oos.writeObject(obj);
        oos.close();
    }

    public static <T extends Serializable> T resurrect(File file, Class<T> c)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file));

        T ret = (T) ois.readObject();
        ois.close();
        return ret;
    }
}