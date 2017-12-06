package bankWindow;

import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/6.
 */
public class VIPWindow implements Runnable {
    private Clinet client;

    public VIPWindow(Clinet client) {
        this.client = client;
    }

    @Override
    public void run() {
        Custom cs = null;
        while ((cs = client.nextCustom(CustomType.VIP)) != null) {
            long waitedTime = System.currentTimeMillis() - client.getServiceStartTime();
            try {
                System.out.println("VIP Window is Servicing for Custom " + cs.getOrder()
                        + ", Custom Type: " + cs.getCustomType().name() + ", arrival time: "
                        + String.format("%1$tF %1$tR", cs.getArrivalDate()) + ", waited time: "
                        + waitedTime/1000 + "S");
                // 服务时间
                TimeUnit.MINUTES.sleep(cs.getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
