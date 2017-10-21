import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by qtfs on 2017/9/21.
 */
public class TwinsLoackTest {
    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while(true) {
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(5);
                    }catch(InterruptedException ex) {

                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        for(int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println();
            }catch(InterruptedException ex) {

            }

        }
    }
}
