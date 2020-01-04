package pt.com.santos.util.time;

import java.util.Date;

public class DateUtilities {

    public static long difference(Date a, Date b) {
        return (a.getTime() - b.getTime());
    }
}
