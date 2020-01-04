package pt.com.santos.util.scheduling;

import java.util.Date;

public class ModifiableScheduleIterator implements ScheduleIterator {

    private long period;
    private Date next;

    public ModifiableScheduleIterator(long delay, long period) {
        long start = System.currentTimeMillis();
        this.period = period;
        this.next = new Date(start + delay);
    }

    public void setNext(Date next) {
        this.next = next;
    }

    public Date next() {
        Date res = next;
        next = new Date(res.getTime() + period);
        return res;
    }
}
