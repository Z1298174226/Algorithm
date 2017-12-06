package digraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtfs on 2017/12/6.
 */
public class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private List<Integer> cycle;
    private int[] edgeTo;

    public DirectedCycle(EdgeWeightedGraph G) {
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        for(int i = 0; i < G.getV(); i++) {
            if(!marked[i] && cycle == null)
                dfs(G, i);
        }
    }

    private void dfs(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(cycle != null)
                return;
            if(!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }else if(onStack[w]) {
                cycle = new ArrayList<Integer>();
                for(int vertex = v; vertex != w; vertex = edgeTo[vertex])
                    cycle.add(vertex);
                cycle.add(w);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
