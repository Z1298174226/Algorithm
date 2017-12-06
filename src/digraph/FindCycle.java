package digraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtfs on 2017/12/6.
 */
public class FindCycle {
    boolean[] marked;
    boolean[] onStack;
    List<Integer> cycle;
    int[] edgeTo;
    public FindCycle(EdgeWeightedGraph G) {
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        edgeTo = new int[G.getV()];

        for(int i = 0; i < G.getV(); i++) {
            if(!marked[i] && cycle == null) {
                dfs(G, i);
            }
        }
    }

    private void dfs(EdgeWeightedGraph G, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;
        for(DirectedEdge e : G.adj(vertex)) {
            int w = e.to();
            if(cycle != null)
                return;
            if(!marked[w]) {
                edgeTo[w] = vertex;
                dfs(G, w);
            }
            else if(onStack[w]) {
                cycle = new ArrayList();
               for(int v = vertex; v != w; v = edgeTo[v]) {
                   cycle.add(v);
               }
               cycle.add(w);
            }
        }
        onStack[vertex] = false;
    }

    public boolean findCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
