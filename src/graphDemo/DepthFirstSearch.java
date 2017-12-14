package graphDemo;

import digraph.DirectedEdge;
import digraph.EdgeWeightedGraph;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by qtfs on 2017/12/8.
 */
public class DepthFirstSearch {
    boolean[] marked;
    List<Integer> postQueue;
    List<Integer> preQueue;

    public DepthFirstSearch(EdgeWeightedGraph G) {
        marked = new boolean[G.getV()];
        postQueue = new ArrayList<Integer>();
        preQueue = new ArrayList<Integer>();

        for(int i = 0; i < G.getV(); i++) {
            if(!marked[i])
                dfs(G, i);
        }
    }

    private void dfs(EdgeWeightedGraph G, int vertex) {
        for(DirectedEdge e : G.adj(vertex)) {
            preQueue.add(vertex);
            int w = e.to();
            if(!marked[w])
                dfs(G, w);
            postQueue.add(vertex);
        }
    }

    public Iterable<Integer> reversePostQueue() {
        return new Iterable<Integer>() {

            @NotNull
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    int current = postQueue.size() - 1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public Integer next() {
                        return postQueue.get(current--);
                    }
                };
            }
        };
    }


}
