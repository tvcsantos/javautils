package pt.com.santos.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

public class EventProducerUtilities {

    private EventProducerUtilities() {
        throw new UnsupportedOperationException();
    }

    public static void notifyListeners(
            List<? extends EventListener> listeners, String method,
                EventObject eo) {
        for (EventListener listener : listeners) {
            Class<? extends EventListener> aClass = listener.getClass();
            try {
                Method theMethod = aClass.getMethod(method, eo.getClass());
                try {
                    boolean accessible = theMethod.isAccessible();
                    if (!accessible) theMethod.setAccessible(true);
                    theMethod.invoke(listener, eo);
                    if (!accessible) theMethod.setAccessible(false);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    continue;
                } catch (IllegalArgumentException ex) {
                    continue;
                } catch (InvocationTargetException ex) {
                    continue;
                }
            } catch (NoSuchMethodException ex) {
                continue;
            } catch (SecurityException ex) {
                continue;
            }
        }
    }
}
