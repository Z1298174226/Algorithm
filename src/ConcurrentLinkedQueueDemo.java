import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/9.
 */
public class ConcurrentLinkedQueueDemo {
    private Random rand = new Random();
    private CountDownLatch latch = new CountDownLatch(1);
    private ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();
    private Object object = new Object();
//    private Queue<Integer> queue = new LinkedList();
//    private Runnable addTask = new AddElement();
//    private Runnable pollTask = new PollElement();
    class AddElement implements Runnable {
        @Override
        public void run() {
            try {
                latch.await();
                int num = rand.nextInt(20);
                queue.add(num);
                System.out.println(String.format("Now we add %2d to the queue", num));
            }catch(InterruptedException ex) {

            }
        }
    }
    class PollElement implements Runnable {
        @Override
        public void run() {
            try{
                latch.await();
                System.out.println("Now we get : " + queue.poll());
                System.out.println("The size of queue is : " + queue.size());
            }catch(InterruptedException ex) {

            }

        }
    }
    public static void main(String[] args) {
        ConcurrentLinkedQueueDemo demo = new ConcurrentLinkedQueueDemo();
        for(int i = 0; i < 3; i++) {
            new Thread(demo.new AddElement()).start();
        }
        for(int i = 0; i < 3; i++) {
            new Thread(demo.new PollElement()).start();
        }
        demo.latch.countDown();
    }
}
