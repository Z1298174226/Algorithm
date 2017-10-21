import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qtfs on 2017/9/27.
 */
public class ReentrantLockDemo implements Runnable{
        ReentrantLock lock = new ReentrantLock();
        Lock lockNew = new ReentrantLock();
        public void get() {
            lockNew.lock();
            try {
                System.out.println(Thread.currentThread().getId());
                set();
            }finally {
                lockNew.unlock();
            }
        }
        public void set() {
            lockNew.lock();
            try {
                System.out.println(Thread.currentThread().getId());
            }finally {
                lockNew.unlock();
            }
        }
        @Override
        public void run() {
            get();
        }
        public static void main(String[] args) {
            ReentrantLockDemo ss = new ReentrantLockDemo();
            new Thread(ss).start();
            new Thread(ss).start();
            new Thread(ss).start();
        }
}
