import edu.princeton.cs.algs4.*;

import java.util.Iterator;

/**
 * Created by qtfs on 2017/12/5.
 */
public class AcycleSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcycleSP(EdgeWeightedDigraph G, int s) {
        this.distTo = new double[G.V()];
        this.edgeTo = new DirectedEdge[G.V()];

        for(int v = 0; v < G.V(); ++v) {
            this.distTo[v] = Double.POSITIVE_INFINITY;
        }
        this.distTo[s] = 0.0;
        Topological topological = new Topological(G);
        if(!topological.hasOrder()) {
            throw new IllegalArgumentException("Digraph is not acyclic.");
        } else {
            Iterator i$ = topological.order().iterator();

            while(i$.hasNext()) {
                int v = ((Integer)i$.next()).intValue();
                Iterator i = G.adj(v).iterator();

                while(i.hasNext()) {
                    DirectedEdge e = (DirectedEdge)i.next();
                    this.relax(e);
                }
            }

        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if(this.distTo[w] > this.distTo[v] + e.weight()) {
            this.distTo[w] = this.distTo[v] + e.weight();
            this.edgeTo[w] = e;
        }

    }

    public double distTo(int v) {
        return this.distTo[v];
    }

    public boolean hasPathTo(int v) {
        return this.distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if(!this.hasPathTo(v)) {
            return null;
        } else {
            Stack<DirectedEdge> path = new Stack();

            for(DirectedEdge e = this.edgeTo[v]; e != null; e = this.edgeTo[e.from()]) {
                path.push(e);
            }

            return path;
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        AcycleSP sp = new AcycleSP(G, s);

        for(int v = 0; v < G.V(); ++v) {
            if(!sp.hasPathTo(v)) {
                StdOut.printf("%d to %d         no path\n", new Object[]{Integer.valueOf(s), Integer.valueOf(v)});
            } else {
                StdOut.printf("%d to %d (%.2f)  ", new Object[]{Integer.valueOf(s), Integer.valueOf(v), Double.valueOf(sp.distTo(v))});
                Iterator i$ = sp.pathTo(v).iterator();

                while(i$.hasNext()) {
                    DirectedEdge e = (DirectedEdge)i$.next();
                    StdOut.print(e + "   ");
                }

                StdOut.println();
            }
        }

    }
}
