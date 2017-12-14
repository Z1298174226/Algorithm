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
        while (true) {
            try {
                client.lock.lock();
                try {
                    if (Clinet.queue.isEmpty())
                        client.empty.await();
                    boolean marked = false;
                    for(Custom custom : Clinet.queue) {
                        if(custom.getCustomType() == CustomType.VIP) {
                            cs = custom;
                            Clinet.queue.remove(custom);
                            marked = true;
                            break;
                        }
                    }
                    if(!marked)
                        cs = Clinet.queue.poll();
                    long waitedTime = System.currentTimeMillis() - client.getServiceStartTime() - cs.getArrivalDate().getMinutes() * 60 * 1000;
                    System.out.println("VIP Window is Servicing for Custom " + cs.getOrder()
                            + ", Custom Type: " + cs.getCustomType().name() + ", arrival time: "
                            + String.format("%1$tF %1$tR", cs.getArrivalDate()) + ", waited time: "
                            + waitedTime / 1000 / 60 + "min");
                }finally {
                    client.lock.unlock();
                }
                TimeUnit.MINUTES.sleep(cs.getServiceTime());
            } catch (InterruptedException ex) {
                System.out.println("==========The VIP service is interrupted===========");
            }
        }
    }
}
