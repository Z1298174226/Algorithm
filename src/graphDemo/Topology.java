package graphDemo;

import digraph.EdgeWeightedGraph;

/**
 * Created by qtfs on 2017/12/8.
 */
public class Topology {
    Iterable<Integer> order;
    public Topology (EdgeWeightedGraph G) {
        FindCycle findCycle = new FindCycle(G);
        if(findCycle.hasCycle()) {
            throw new IllegalArgumentException("=========The Graph has cycle========");
        }
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(G);
        order = depthFirstSearch.reversePostQueue();
    }

    public boolean hasOrder() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}
