package com.zhao.lex.IBMInterview;

import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/7.
 */
public class QueueManager implements Runnable {

    private Client client;

    public QueueManager(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while(true) {
            Custom cs = client.nextCustom();
            if(cs == null) break;
            long sleepTime = cs.getCustomArriveTime().getMinutes() * 60 - (System.currentTimeMillis() - client.clientStartTime) / 1000;
            if(sleepTime > 0) {
                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                }catch(InterruptedException  ex) {

                }
            }
            client.lock.lock();
            try{
                System.out.println(String.format("The Customer%3d is comming, ", cs.getId()) + "now is : " + String.format("%1$tF %1$tR", cs.getCustomArriveTime()));
                Client.queue.add(cs);
                client.empty.signal();
                client.normalEmpty.signal();
            }finally {
                client.lock.unlock();
            }
        }
    }
}
