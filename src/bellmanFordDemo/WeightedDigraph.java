package bellmanFordDemo;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;

public class WeightedDigraph {
     private int V;
     private int E;
     private List<WeightedEdge>[] adj;
    
     @SuppressWarnings("unchecked")
     public WeightedDigraph(int V){
    	 this.V = V;
    	 adj = (ArrayList<WeightedEdge>[]) new ArrayList[V];
    	 for(int i = 0;i < V;i++){
    		 adj[i] = new ArrayList<WeightedEdge>();
    	 }
     }
     
    
     public WeightedDigraph(In in){
    	 this(in.readInt());
	        int E = in.readInt();
	        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
	        for (int i = 0; i < E; i++) {
	            int v = in.readInt();
	            int w = in.readInt();
	            double weight = in.readDouble();
	            addEdge(new WeightedEdge(v, w, weight));
	        }
     }
     
     public void addEdge(WeightedEdge e){
    	 int v = e.from();
    	 adj[v].add(e);
    	 E++;
     }
     
     public Iterable<WeightedEdge> adj(int v){
    	 return adj[v];
     }
     public int V(){
    	 return V;
     }
     public int E(){
    	 return E;
     }
}
