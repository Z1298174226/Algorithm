package bankWindow;

import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/7.
 */
public class QueueManager implements Runnable{

    private Clinet clinet;
    private long addQueueStartTime;

    public QueueManager(Clinet clinet, long addQueueStartTime) {
        this.clinet = clinet;
        this.addQueueStartTime = addQueueStartTime;
    }

    @Override
    public void run() {
        Custom cs;
            while (true) {
                cs = clinet.nextCustom();
                if (cs == null) break;
                long sleepTime = cs.getArrivalDate().getMinutes() * 60 - (System.currentTimeMillis() - addQueueStartTime) / 1000;
                if (sleepTime > 0) {
                    try {
                        TimeUnit.SECONDS.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                    }
                }
                System.out.println(String.format("The Customer %d is comming, ", cs.getOrder()) + "now is : " + String.format("%1$tF %1$tR", cs.getArrivalDate()));
                clinet.lock.lock();
                try {
                    Clinet.queue.add(cs);
                    clinet.empty.signal();
                }finally {
                    clinet.lock.unlock();
                }
            }
        }
}
