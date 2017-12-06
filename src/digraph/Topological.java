package digraph;

/**
 * Created by qtfs on 2017/12/6.
 */
public class Topological {
    Iterable<Integer> order;

    public Topological(EdgeWeightedGraph G) {
        DirectedCycle directedCycle = new DirectedCycle(G);
      //  FindCycle findCycle = new FindCycle(G);
      //  if(!findCycle.findCycle()) {
        if(!directedCycle.hasCycle()) {
            DepthFirstOrder directedFirstOrder = new DepthFirstOrder(G);
            order = directedFirstOrder.reservePostOrder();
        }
        else {
            throw new IllegalArgumentException("The graph has cycle!");
        }
    }

    public boolean hasOrder() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}
