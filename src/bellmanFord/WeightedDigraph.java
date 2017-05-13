package bellmanFord;


import java.util.ArrayList;
import java.util.List;

import digraph.DirectedEdge;
import edu.princeton.cs.algs4.In;

public class WeightedDigraph implements WeightedDigraphImplement{
      private int V;
      private int E;
      private List<Edge>[] adj;
      @SuppressWarnings("unchecked")
      public WeightedDigraph(int V){
    	  this.V = V;
    	  this.E = 0;
    	  adj = (List<Edge>[]) new ArrayList[V];
    	  for(int i = 0;i < V;i++){
    		  adj[i] = new ArrayList<Edge>();
    	  }
      }
      public WeightedDigraph(In in) {
          this(in.readInt());
          int E = in.readInt();
          if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
          for (int i = 0; i < E; i++) {
              int v = in.readInt();
              int w = in.readInt();
              double weight = in.readDouble();
              addEdge(new Edge(v, w, weight));
          }
      }
	@Override
	public void addEdge(Edge e) {
		int v = e.from(); 
		adj[v].add(e);	
		E++;
	}
	@Override
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	@Override
	public Iterable<Edge> edges() {
		List<Edge> edges = new ArrayList<Edge>();
		for(int i= 0 ;i < V;i++){
			for(Edge e:adj[i]){
				edges.add(e);
			}
		}
		return edges;
	}
	@Override
	public int V() {
		return V;
	}
	@Override
	public int E() {
		return E;
	}
}
