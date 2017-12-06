

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qtfs on 2017/11/27.
 */
public  class BoundQueue<T>  {
    private final int size;
    public BoundQueue(int size) {
        this.size = size;
    }
    Lock lock = new ReentrantLock();
    Condition notEmpty = lock.newCondition();
    Condition notFull = lock.newCondition();
    Queue<T> queue = new LinkedList<T>();
    CountDownLatch latch = new CountDownLatch(1);
    private int count = 0;
    public void add(T element) {
        try {
            latch.await();
        } catch (InterruptedException ex) {

        }
        lock.lock();
        try{
            while(count == size) {
                notFull.await();
                System.out.println("The queue is full, you can't add");
            }
            queue.add(element);
            count++;
            notEmpty.signal();

        } catch (InterruptedException ex) {

        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        try {
            latch.await();
        } catch (InterruptedException ex) {

        }
        lock.lock();
        try {
            while(count == 0) {
                notEmpty.await();
                System.out.println("The queue is Empty, you can't poll");
            }
            T result = queue.poll();
            count--;
            notFull.signal();
            return result;
        } catch (InterruptedException ex) {

        } finally {
            lock.unlock();
        }
        Object x = new Object();
        return (T) x;
    }

    public static void main(String[] args) {
        BoundQueue<Integer> queue = new BoundQueue<>(20);
        Runnable task_one = new Runnable() {
            @Override
            public void run() {
                Random rand = new Random();
                try {
                    for (; ; ) {
                        queue.add(rand.nextInt(200));
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch(InterruptedException ex) {

                }
            }
        };
        Runnable task_two = new Runnable() {
            @Override
            public void run() {
                try {
                    for (; ; ) {
                        System.out.println(queue.poll());
                        TimeUnit.SECONDS.sleep(2);
                    }
                }catch(InterruptedException ex) {

                }
            }
        };
        for(int i = 0; i < 5; i++) {
            new Thread(task_one).start();
        }
        for(int i = 0; i < 3; i++) {
            new Thread(task_two).start();
        }
        queue.latch.countDown();
    }

}
