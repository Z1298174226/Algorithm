package bellmanFord;

import digraph.DirectedEdge;
import digraph.EdgeWeightedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtfs on 2017/12/11.
 */
public class FindNegativeCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    List<Integer> cycle;

    public FindNegativeCycle(EdgeWeightedGraph G) {
        marked = new boolean[G.getV()];
        onStack = new boolean[G.getV()];
        edgeTo = new int[G.getV()];

        for(int i = 0; i < G.getV(); i++) {
            if(!marked[i])
                dfs(G, i);
        }
    }

    private void dfs(EdgeWeightedGraph G, int vertex) {
        marked[vertex] = true;
        onStack[vertex] = true;
        for(DirectedEdge e : G.adj(vertex)) {
            int w = e.to();
            if(cycle != null) return;
            else if(!marked[w]) {
                dfs(G, w);
                edgeTo[w] = vertex;
            }
            else if(onStack[w]) {
                cycle = new ArrayList<Integer>();
//                for(int v = vertex; v != w; v = edgeTo[v])
//                    cycle.add(v);
                cycle.add(w);
            }
        }
        onStack[vertex] = false;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

}
