package digraph;

import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by qtfs on 2017/12/29.
 */
public class IndexMinQueue<Key extends Comparable<Key>> {
    private int[] pq;
    private int[] qp;
    private Key[] values;
    private int cont = 0;
    private int maxN;

    public IndexMinQueue(int maxN) {
        this.maxN = maxN;
        this.pq = new int[maxN + 1];
        this.qp = new int[maxN + 1];
        this.values = (Key[]) new Comparable[maxN + 1];
        Arrays.fill(qp, -1);
    }

    public boolean contains(int i) {
        if(i >= 0 && i <= maxN) {
            return this.qp[i] != -1;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean greater(int i, int j) {
        return this.values[this.pq[i]].compareTo(this.values[this.pq[j]]) > 0;
    }

    public void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        this.qp[this.pq[i]] = i;
        this.qp[this.pq[j]] = j;
    }
    public void insert(int key, Key value) {
        if(key >= 0 && key < maxN) {
            if(contains(key))
                throw new IllegalArgumentException("The index is already in the queue...");
            else {
                pq[++cont] = key;
                qp[key] = cont;
                values[key] = value;
                swim(cont);
            }
        }
        else
            throw new IndexOutOfBoundsException("The key is out of bounds");
    }

    private void swim(int i) {
        int parents = i >>> 1;
        while(parents > 1 && greater(parents, i)) {
            exch(i, parents);
            parents = parents >>> 1;
        }
    }

    public int delMin() {
        if(cont < 1)
            throw new IllegalArgumentException("There is no element to poll...");
        int result = pq[1];
        exch(1, cont--);
        sink(1);
        qp[result] = -1;
        values[result] = null;
        pq[cont + 1] = -1;

        return result;
    }

    private void sink(int i) {
        int half = cont >>> 1;
        while (i <= half) {
            int leftChild = i << 1;
            if (leftChild < cont && greater(leftChild, leftChild + 1))
                leftChild++;
            if (greater(i, leftChild)) {
                exch(i, leftChild);
                i = leftChild;
                continue;
            }
            return;
        }
    }

    private void delete(int i) {
        if(cont < 1)
            throw new IllegalArgumentException("There is no element to poll...");
        int index = qp[i];
        exch(index, cont--);
        swim(index);
        sink(index);
        qp[i] = -1;
        values[i] = null;
        pq[cont + 1] = -1;
    }

    public void decreaseKey(int i, Key key) {
        if(i >= 0 && i < this.maxN) {
            if(!this.contains(i)) {
                throw new NoSuchElementException("index is not in the priority queue");
            } else if(this.values[i].compareTo(key) <= 0) {
                throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
            } else {
                this.values[i] = key;
                this.swim(this.qp[i]);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void increaseKey(int i, Key key) {
        if(i >= 0 && i < this.maxN) {
            if(!this.contains(i)) {
                throw new NoSuchElementException("index is not in the priority queue");
            } else if(this.values[i].compareTo(key) >= 0) {
                throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
            } else {
                this.values[i] = key;
                this.sink(this.qp[i]);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    public static void main(String[] args) throws InterruptedException {

    }

}
