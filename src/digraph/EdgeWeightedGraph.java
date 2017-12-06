package digraph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qtfs on 2017/12/6.
 */
public class EdgeWeightedGraph {
    private int V;
    private int E;
    private List<DirectedEdge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (List<DirectedEdge>[]) new ArrayList[V];
        for(int i = 0; i < V; i++)
            adj[i] = new ArrayList<DirectedEdge>();
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        this.E = in.readInt();
        for(int i = 0; i < E; i++) {
            int from = in.readInt();
            int to = in.readInt();
            double weight = in.readDouble();
            int to1 = in.readInt();
            int from1 = in.readInt();
            double weight1 = in.readDouble();
            addEdge(new DirectedEdge(from, to, weight));
        }
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public Iterable<DirectedEdge> allPath() {
        List<DirectedEdge> list = new ArrayList<DirectedEdge>();
        for(int i = 0; i < V; i++) {
            for(DirectedEdge e : adj(i))
                list.add(e);
        }
        return list;
    }
}
