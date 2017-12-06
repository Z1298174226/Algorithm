package acyclicSP;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;

/**
 * Created by qtfs on 2017/12/6.
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(EdgeWeightedDigraph G) {
        this.marked = new boolean[G.V()];
        this.onStack = new boolean[G.V()];
        this.edgeTo = new int[G.V()];

        for(int v = 0; v < G.V(); v++) {
            if(!marked[v] && cycle == null)
                dfs(G, v);

        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        Iterator i = G.adj(v).iterator();
        while(true) {
            while(i.hasNext()) {
                int w = ((Integer)i.next()).intValue();
                if(cycle != null)
                    return;
                if(!marked[w]) {
                    edgeTo[w] = v;
                    dfs(G, w);
                }else if(onStack[w]) {
                    cycle = new Stack();
                    for(int x= v; x != w; x = edgeTo[x])
                        cycle.push(Integer.valueOf(x));

                    cycle.push(Integer.valueOf(w));
                    cycle.push(Integer.valueOf(v));
                }
            }
            onStack[v] = false;
            return;
        }
    }

    public boolean hasCycle() {
        return this.cycle != null;
    }

    public Iterable<Integer> cycle() {
        return this.cycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        DirectedCycle finder = new DirectedCycle(G);
        if(finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            Iterator i$ = finder.cycle().iterator();

            while(i$.hasNext()) {
                int v = ((Integer)i$.next()).intValue();
                StdOut.print(v + " ");
            }

            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }

        StdOut.println();
    }

}
