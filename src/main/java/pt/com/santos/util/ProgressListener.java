package pt.com.santos.util;

import java.util.EventListener;

public interface ProgressListener extends EventListener {
    public void progressStart(ProgressEvent pe);

    public void progressUpdate(ProgressEvent pe);

    public void progressFinish(ProgressEvent pe);

    public void progressInterrupt(ProgressEvent pe);
}
