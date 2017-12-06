package digraph;

import edu.princeton.cs.algs4.In;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qtfs on 2017/12/6.
 */
public class AcyclicLP {
    double[] distTo;
    DirectedEdge[] edgeTo;

    public AcyclicLP(EdgeWeightedGraph G, int source) {
        distTo = new double[G.getV()];
        edgeTo = new DirectedEdge[G.getV()];
        digraph.Topological topological = new digraph.Topological(G);
        Arrays.fill(distTo, Double.NEGATIVE_INFINITY);
        distTo[source] = 0.0;
        if(!topological.hasOrder()) {
            throw new IllegalArgumentException("The Graph is not acyclic!");
        }
        for(Integer i : topological.order()) {
            for(DirectedEdge e : G.adj(i))
                relax(e);
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if(distTo[w] < distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
        }
    }

    public boolean hasPathTo(int vertex) {
        return distTo[vertex] > Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int vertex) {
        if(!hasPathTo(vertex)) return null;
        return new Iterable<DirectedEdge>() {
            @NotNull
            @Override
            public Iterator<DirectedEdge> iterator() {
                List<DirectedEdge> list = new ArrayList();
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
        In in = new In("src\\rome99.txt");
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        int source = 5;//Integer.parseInt(args[1]);
        AcyclicLP sp = new AcyclicLP(G, source);
        for(int i = 0; i < G.getV(); i++) {
            if(sp.hasPathTo(i)) {
                System.out.print(String.format("%d to %d (%.2f)  : ", source, i, sp.distTo[i]));
                for (DirectedEdge e : sp.pathTo(i)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            }
            else
                System.out.println(String.format("There is no path to %d", i));
        }
    }
}
