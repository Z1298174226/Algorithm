package digraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
//import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by qtfs on 2017/12/14.
 */
public class RealDijkstra {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> queue;
    private boolean[] marked;

    public RealDijkstra(EdgeWeightedGraph G, int source) {
        distTo = new double[G.getV()];
        edgeTo = new DirectedEdge[G.getV()];
        queue = new IndexMinPQ<>(G.getE());
        marked = new boolean[G.getV()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0.0;
        queue.insert(source, distTo[source]);

        for(int k = 1; k < G.getV(); k++) {
            int vertex = queue.delMin();
            marked[vertex] = true;
            for(DirectedEdge e : G.adj(vertex)) {
                int w = e.to();
                if(!marked[w] && distTo[w] > distTo[vertex] + e.getWeight()) {
                    distTo[w] = distTo[vertex] + e.getWeight();
                    edgeTo[w] = e;
//                    if(queue.contains(w)) queue.decreaseKey(w, distTo[w]);
//                    else queue.insert(w, distTo[w]);
                    if(!queue.contains(w)) queue.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<DirectedEdge> pathTo(final int vertex) {
        return new Iterable<DirectedEdge>() {

   //         @NotNull
            @Override
            public Iterator<DirectedEdge> iterator() {
                List<DirectedEdge> list = new ArrayList<DirectedEdge>();
                for(DirectedEdge e = edgeTo[vertex]; e != null; e = edgeTo[e.from()]) {
                    list.add(e);
                }
                return new Iterator<DirectedEdge>() {
                    private int current = list.size() - 1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public DirectedEdge next() {
                        return list.get(current--);
                    }
                };
            }
        };
    }

    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }

    public Double path(int vertex) {
        return distTo[vertex];
    }

    public static void main(String[] args) {
        In in = new In("src\\path\\tinyEWD.txt");
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        int source = 1;
        RealDijkstra sp = new RealDijkstra(G, source);
        for(int i = 0; i < G.getV(); i++) {
            if(!sp.hasPathTo(i))
                System.out.println(String.format("There is no path from %d to %d", source, i));
            else {
                System.out.print(String.format("The path from %d to %d (%.2f) is : ", source, i, sp.path(i)));
                for (DirectedEdge e : sp.pathTo(i))
                    System.out.print(e + " ");
                System.out.println();
            }
        }
    }
}
