package digraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtfs on 2017/12/6.
 */
public class DirectedEdge {
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }
    public int to() {
        return w;
    }
    public double getWeight() {
        return weight;
    }
    public String toString() {
        return String.format("%d -> %d  (%.2f)", v, w, weight);
    }


}
