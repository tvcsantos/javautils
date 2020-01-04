package pt.com.santos.util;

import java.util.EventListener;

public interface ProcessListener extends EventListener {
    public void processStart(ProcessEvent pe);

    public void processUpdate(ProcessEvent pe);

    public void processFinish(ProcessEvent pe);

    public void processInterrupt(ProcessEvent pe);
}
