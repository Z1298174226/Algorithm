import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by qtfs on 2017/10/17.
 */
public class ReconstructItinerary {
    public static void main(String[] args) {
        Map<String, PriorityQueue<String>> target = new HashMap<String, PriorityQueue<String>>();
        System.out.println(target.computeIfAbsent("Tom", k -> new PriorityQueue<>()).add("Jetty"));
        System.out.println(target.putIfAbsent("Tom", new PriorityQueue<>()));
        System.out.println(target.putIfAbsent("Linda", new PriorityQueue<>()));

    }
}
