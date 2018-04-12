package bellmanFord;

import digraph.DirectedEdge;
import digraph.EdgeWeightedGraph;
import digraph.FindCycle;
import edu.princeton.cs.algs4.In;
//import org.jetbrains.annotations.NotNull;


import java.util.*;

/**
 * Created by qtfs on 2017/12/11.
 */
public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private Queue<Integer> queue;
    private boolean[] onQueue;
    private Iterable<Integer> cycle;
    private int cost = 0;
    public BellmanFordSP(EdgeWeightedGraph G, int source) {
        distTo = new double[G.getV()];
        edgeTo = new DirectedEdge[G.getV()];
        queue = new LinkedList<Integer>();
        onQueue = new boolean[G.getV()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0.0;
        queue.add(source);
        while(!queue.isEmpty() && !hasNegativeCycle()) {
            int vertex = queue.poll();
           // onQueue[vertex] = false;
            relax(G, vertex);
        }
        if(hasNegativeCycle())
            throw new IllegalArgumentException("There is a negarive cycle in the graph");
    }

    private void relax(EdgeWeightedGraph G, int vertex) {
        for(DirectedEdge e : G.adj(vertex)) {
            int w = e.to();
            if(distTo[w] > distTo[vertex] + e.getWeight()) {
                distTo[w] = distTo[vertex] + e.getWeight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.add(w);
                    onQueue[w] = true;
                }
            }
            if(cost++ % G.getV() == 0)
                findNegativeCycle();
            if(hasNegativeCycle())
                return;
        }
    }
    private boolean hasNegativeCycle() {
        return cycle != null;
    }

    private void findNegativeCycle() {
        int length = edgeTo.length;
        EdgeWeightedGraph graph = new EdgeWeightedGraph(length);
        for(int i = 0; i < length; i++) {
            if(edgeTo[i] != null)
                graph.addEdge(edgeTo[i]);
        }
        FindNegativeCycle negative = new FindNegativeCycle(graph);
        cycle = negative.cycle();
    }

    public boolean hasPathto(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int vertex) {
        return new Iterable<DirectedEdge>() {

      //      @NotNull
            @Override
            public Iterator<DirectedEdge> iterator() {
                List<DirectedEdge> list = new ArrayList<DirectedEdge>();
                for(DirectedEdge e = edgeTo[vertex]; e != null; e = edgeTo[e.from()]) {
                    list.add(e);
                }
                return new Iterator<DirectedEdge>() {
                    int current = list.size() - 1;
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

    public static void main(String[] args) {
        In in = new In("src\\path\\tinyEWD.txt");
        int source = 5;
        EdgeWeightedGraph G  = new EdgeWeightedGraph(in);
        BellmanFordSP bellmanFordSP = new BellmanFordSP(G, source);
        for(int i = 0; i < G.getV(); i++) {
            System.out.println(String.format("The path from %d to %d is %.3f: ", source, i, bellmanFordSP.distTo[i]));
            if(bellmanFordSP.hasPathto(i)) {
                for (DirectedEdge e : bellmanFordSP.pathTo(i)) {
                    System.out.print(e + "  ");
                }
                System.out.println();
            }
            else {
                System.out.println("There is no path to " + i);
            }
        }
    }
}
