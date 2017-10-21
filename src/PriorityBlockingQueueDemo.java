import java.util.Comparator;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qtfs on 2017/10/19.
 */
public class PriorityBlockingQueueDemo<E> {

    private transient Object[] queue;

    private final ReentrantLock lock;

    private final Condition notEmpty;

    private transient Comparator<? super E> comparator;

    public PriorityBlockingQueueDemo(int initialCapacity, Comparator<? super E> comparator) {
        assert initialCapacity >= 1;
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.comparator = comparator;
        this.queue = new Object[initialCapacity];
    }


}
