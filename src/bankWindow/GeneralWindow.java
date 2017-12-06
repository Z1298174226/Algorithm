package bankWindow;

import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/6.
 */
public class GeneralWindow implements Runnable {
    private String windowName;
    private Clinet client;

    public GeneralWindow(Clinet client, String winName) {
        this.client = client;
        this.windowName = winName;
    }

    public void run() {
        Custom cs = null;
        while((cs = client.nextCustom(CustomType.COMMON)) != null) {
            long waitedTime = System.currentTimeMillis() - client.getServiceStartTime();
            try {
                System.out.println("General Window(" + this.windowName + ") is Servicing for Custom "
                        + cs.getOrder() + ", Custom Type: " + cs.getCustomType().name() + ", arrival time: "
                        + String.format("%1$tF %1$tR", cs.getArrivalDate()) + ", waited time: "
                        + waitedTime/1000 + "S");
                TimeUnit.MINUTES.sleep(cs.getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
}
