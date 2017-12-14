package com.zhao.lex.IBMInterview;

import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/7.
 */
public class NormalWindow implements Runnable {

    private String windowName;
    private Client client;

    public NormalWindow(String windowName, Client client) {
        this.windowName = windowName;
        this.client = client;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Custom cs = null;
                TimeUnit.MILLISECONDS.sleep(10);
                client.lock.lock();
                try {
                    if (Client.queue.isEmpty())
                        client.empty.await();
                    for(Custom custom : Client.queue) {
                        if(custom.getCustomType() == CustomType.NORMAL) {
                            cs = custom;
                            Client.queue.remove(custom);
                            break;
                        }
                    }
                    if(cs == null) {
                        client.normalEmpty.await();
                    }
                    long waitedTime = System.currentTimeMillis() - client.getClientStartTime() - cs.getCustomArriveTime().getMinutes() * 60 * 1000;
                    System.out.println("General Window(" + this.windowName + ") is Servicing for Custom "
                            + cs.getId() + ", Custom Type: " + cs.getCustomType().name() + ", arrival time: "
                            + String.format("%1$tF %1$tR", cs.getCustomArriveTime()) + ", waited time: "
                            + waitedTime / 1000 / 60 + "min");
                } finally {
                    client.lock.unlock();
                }
                TimeUnit.MINUTES.sleep(cs.getCustomServiceTime());
            }catch(InterruptedException ex) {

            }
        }
    }
}
