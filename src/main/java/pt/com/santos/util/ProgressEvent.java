package pt.com.santos.util;

import java.util.EventObject;

public class ProgressEvent extends EventObject {

    protected int progress;

    public ProgressEvent(Object source) {
        super(source);
    }

    public ProgressEvent(Object source, int progress) {
        super(source);
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }
}
