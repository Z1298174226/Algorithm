package bellmanFord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFordSP {
	private double[] distTo;
	private Edge[] edgeTo;
	private boolean[] onQueue;
	private Queue<Integer> q;
	private Iterable<Edge> cycle;
	private int cost = 0;
	public BellmanFordSP(WeightedDigraph G , int s){
		distTo = new double[G.V()];
		edgeTo = new Edge[G.V()];
		onQueue = new boolean[G.V()];
		q = new Queue<Integer>();
		for(int i = 0; i < G.V(); i++){
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		q.enqueue(s);
		onQueue[s] = true;
		 while (!q.isEmpty() && !hasNegativeCycle()) {
	            int v = q.dequeue();
	            onQueue[v] = false;
	            relax(G, v);
	        }

	}
	private boolean hasNegativeCycle() {
		return cycle != null;
	}
	private Iterable<Edge> NegariveCycle(){
		return cycle;
	}
	private void relax(WeightedDigraph G, int v) {
		/*
		 *  for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) return;  // found a negative cycle
            }
        }
		 */
		for(Edge e: G.adj(v)){
			int w = e.to();
			if( distTo[v] + e.weight() < distTo[w]){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			    if(!onQueue[w]){
				q.enqueue(w);
				onQueue[w] = true;
			  }
			}
			if(cost++ % G.V() == 0){
				findNegativeCycle();
			    if (hasNegativeCycle()) return;  // found a negative cycle
			}
		}
	}
	private void findNegativeCycle() {
		int num = edgeTo.length;
		WeightedDigraph spt = new WeightedDigraph(num);
		for(int i = 0; i < num; i++){
			Edge e = edgeTo[i];
			if(e != null)
				spt.addEdge(e);			
		}
		NegativeWeightedCycle negative = new NegativeWeightedCycle(spt);
		cycle = negative.cycle();
	}
	public  boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public Iterable<Edge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		return new Iterable<Edge>(){
			
			@Override
			public Iterator<Edge> iterator(){
				List<Edge> path = new ArrayList<Edge>();
				for(Edge e = edgeTo[v];e != null;e = edgeTo[e.from()]){
					path.add(e);
				}
				return new Iterator<Edge>(){
                    private int current = path.size() - 1;
					@Override
					public boolean hasNext() {
					    return current > -1;
					}

					@Override
					public Edge next() {
				        return path.get(current--);
					}
					
					@Override
					public void remove(){
						throw new UnsupportedOperationException();
					}
					
				};
			}
		};
	}
	public static void main(String[] args){
		
		String fileName = args[0];
		In in = new In(fileName);
		//int s = Integer.parseInt(args[1]);
		WeightedDigraph G = new WeightedDigraph(in);
		//EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		for(int i = 0; i < G.V();i++){
		BellmanFordSP bell = new BellmanFordSP(G,i);
		if(bell.hasNegativeCycle()){
			for(Edge e : bell.NegariveCycle()){
				System.out.println(e);
			}
		}
		else{
			for(int j = 0 ;j < G.V();j++){
				if(bell.hasPathTo(j)){
					System.out.print(String.format("%d to %d (%5.2f) : ", i, j, bell.distTo[j]));
					for(EdgeImplement e:bell.pathTo(j)){
						System.out.print( e + "  ");
					}
					System.out.println();
				}
				else{
					System.out.println("no path!!!");
				}
			 }
		 }
		}
		/*
		  In in = new In(args[0]);
	        int s = Integer.parseInt(args[1]);
	        WeightedDigraph G = new WeightedDigraph(in);

	        BellmanFordSP sp = new BellmanFordSP(G, s);

	        // print negative cycle
	        if (sp.hasNegativeCycle()) {
	            for (Edge e : sp.NegariveCycle())
	                StdOut.println(e);
	        }

	        // print shortest paths
	        else {
	            for (int v = 0; v < G.V(); v++) {
	                if (sp.hasPathTo(v)) {
	                    StdOut.printf("%d to %d (%5.2f)  ", s, v, sp.distTo[v]);
	                    for (Edge e : sp.pathTo(v)) {
	                        StdOut.print(e + "   ");
	                    }
	                    StdOut.println();
	                }
	                else {
	                    StdOut.printf("%d to %d           no path\n", s, v);
	                }
	            }
	        }
   */
	    }

}
