package graphDemo;

import digraph.DirectedEdge;
import digraph.EdgeWeightedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtfs on 2017/12/8.
 */
public class FindCycle {
    boolean[] marked;
    boolean[] onList;
    List<Integer> cycle;
    int[] edgeTo;

    public FindCycle(EdgeWeightedGraph G) {
        marked = new boolean[G.getV()];
        onList = new boolean[G.getV()];
        edgeTo = new int[G.getV()];

        for(int i = 0; i < G.getV(); i++) {
            if(!marked[i])
                dfs(G, i);
        }
    }

    public void dfs(EdgeWeightedGraph G, int vertex) {
        marked[vertex] = true;
        onList[vertex] = true;
        for(DirectedEdge e : G.adj(vertex)) {
            int w = e.to();
            if(!marked[w]) {
                edgeTo[w] = vertex;
                dfs(G, w);
            }
            else if(onList[w]) {
                cycle = new ArrayList<Integer>();
                for(int v = vertex; v != w; v = edgeTo[v])
                    cycle.add(v);
                cycle.add(w);
                cycle.add(vertex);
            }
        }
        onList[vertex] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
