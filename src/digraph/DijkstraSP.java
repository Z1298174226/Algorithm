package digraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by qtfs on 2017/12/14.
 */
public class DijkstraSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private IndexMinPQ<Double> queue;
 //   private Queue<Integer> pq;
    private int count = 0;

    public DijkstraSP(EdgeWeightedGraph G, int source) {
        distTo = new double[G.getV()];
        edgeTo = new DirectedEdge[G.getV()];
        queue = new IndexMinPQ<Double>(G.getE());
 //       pq = new LinkedList<Integer>();
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[source] = 0.0;
        queue.insert(source, distTo[source]);
 //       pq.add(source);
//        while(!pq.isEmpty()) {
//            count++;
//            int vertex = pq.poll();
//            relaxPQ(G, vertex);
//        }
        while(!queue.isEmpty()) {
            count++;
            int vertex = queue.delMin();
            relax(G, vertex);
        }
    }

    private void relax(EdgeWeightedGraph G, int vertex) {
        for(DirectedEdge e : G.adj(vertex)) {
            int w = e.to();
            if(distTo[w] > distTo[vertex] + e.getWeight()) {
                distTo[w] = distTo[vertex] + e.getWeight();
                edgeTo[w] = e;
                if (queue.contains(w)) queue.decreaseKey(w, distTo[w]);
                else queue.insert(w, distTo[w]);
            }
        }
    }
/*
    private void relaxPQ(EdgeWeightedGraph G, int vertex) {
        for(DirectedEdge e : G.adj(vertex)) {
            int w = e.to();
            if(distTo[w] > distTo[vertex] + e.getWeight()) {
                distTo[w] = distTo[vertex] + e.getWeight();
                edgeTo[w] = e;
                if(!pq.contains(w))
                    pq.add(w);
            }
        }
    }
    */

    public Iterable<DirectedEdge> pathTo(final int vertex) {
        return new Iterable<DirectedEdge>() {

            @NotNull
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
        In in = new In("src\\path\\1000EWG.txt");
        int source = 10;
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        DijkstraSP sp = new DijkstraSP(G, source);
        for(int i = 0; i < G.getV(); i++) {
            if(!sp.hasPathTo(i))
                System.out.println("There is no path!");
            else {
                System.out.print(String.format("The path from %d to %d (%.2f) is : ", source, i, sp.path(i)));
                for (DirectedEdge e : sp.pathTo(i))
                    System.out.print(e + " ");
                System.out.println();
            }
        }
        System.out.println(sp.count);
    }
}
