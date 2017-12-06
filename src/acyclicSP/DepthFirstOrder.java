package acyclicSP;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;

/**
 * Created by qtfs on 2017/12/6.
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private int[] pre;
    private int[] post;
    private Queue<Integer> preorder;
    private Queue<Integer> postorder;
    private int preCounter;
    private int postCounter;

    public DepthFirstOrder(Digraph G) {
        this.pre = new int[G.V()];
        this.post = new int[G.V()];
        this.postorder = new Queue();
        this.preorder = new Queue();
        this.marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); ++v) {
            if(!this.marked[v]) {
                this.dfs(G, v);
            }
        }

        assert this.check(G);

    }

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        this.pre = new int[G.V()];
        this.post = new int[G.V()];
        this.postorder = new Queue();
        this.preorder = new Queue();
        this.marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); ++v) {
            if(!this.marked[v]) {
                this.dfs(G, v);
            }
        }

    }

    private void dfs(Digraph G, int v) {
        this.marked[v] = true;
        this.pre[v] = this.preCounter++;
        this.preorder.enqueue(Integer.valueOf(v));
        Iterator i$ = G.adj(v).iterator();

        while(i$.hasNext()) {
            int w = ((Integer)i$.next()).intValue();
            if(!this.marked[w]) {
                this.dfs(G, w);
            }
        }

        this.postorder.enqueue(Integer.valueOf(v));
        this.post[v] = this.postCounter++;
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        this.marked[v] = true;
        this.pre[v] = this.preCounter++;
        this.preorder.enqueue(Integer.valueOf(v));
        Iterator i$ = G.adj(v).iterator();

        while(i$.hasNext()) {
            DirectedEdge e = (DirectedEdge)i$.next();
            int w = e.to();
            if(!this.marked[w]) {
                this.dfs(G, w);
            }
        }

        this.postorder.enqueue(Integer.valueOf(v));
        this.post[v] = this.postCounter++;
    }

    public int pre(int v) {
        return this.pre[v];
    }

    public int post(int v) {
        return this.post[v];
    }

    public Iterable<Integer> post() {
        return this.postorder;
    }

    public Iterable<Integer> pre() {
        return this.preorder;
    }

    public Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack();
        Iterator i$ = this.postorder.iterator();

        while(i$.hasNext()) {
            int v = ((Integer)i$.next()).intValue();
            reverse.push(Integer.valueOf(v));
        }

        return reverse;
    }

    private boolean check(Digraph G) {
        int r = 0;

        Iterator i$;
        int v;
        for(i$ = this.post().iterator(); i$.hasNext(); ++r) {
            v = ((Integer)i$.next()).intValue();
            if(this.post(v) != r) {
                StdOut.println("post(v) and post() inconsistent");
                return false;
            }
        }

        r = 0;

        for(i$ = this.pre().iterator(); i$.hasNext(); ++r) {
            v = ((Integer)i$.next()).intValue();
            if(this.pre(v) != r) {
                StdOut.println("pre(v) and pre() inconsistent");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        DepthFirstOrder dfs = new DepthFirstOrder(G);
        StdOut.println("   v  pre post");
        StdOut.println("--------------");

        for(int v = 0; v < G.V(); ++v) {
            StdOut.printf("%4d %4d %4d\n", new Object[]{Integer.valueOf(v), Integer.valueOf(dfs.pre(v)), Integer.valueOf(dfs.post(v))});
        }

        StdOut.print("Preorder:  ");
        Iterator i$ = dfs.pre().iterator();

        int v;
        while(i$.hasNext()) {
            v = ((Integer)i$.next()).intValue();
            StdOut.print(v + " ");
        }

        StdOut.println();
        StdOut.print("Postorder: ");
        i$ = dfs.post().iterator();

        while(i$.hasNext()) {
            v = ((Integer)i$.next()).intValue();
            StdOut.print(v + " ");
        }

        StdOut.println();
        StdOut.print("Reverse postorder: ");
        i$ = dfs.reversePost().iterator();

        while(i$.hasNext()) {
            v = ((Integer)i$.next()).intValue();
            StdOut.print(v + " ");
        }

        StdOut.println();
    }
}

