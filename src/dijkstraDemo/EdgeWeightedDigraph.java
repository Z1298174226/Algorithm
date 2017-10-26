package dijkstraDemo;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;

public class EdgeWeightedDigraph {
	private long V;
	private long E;
	//private List<DirectedEdge>[] adj;
	private List<DirectedEdge>[] adj;
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(long V){
		this.V = V;
		this.E = 0;
		this.adj = (List<DirectedEdge>[]) new ArrayList[(int)V];
		
		for (int v = 0;v<V ; v++){
			adj[v] = new ArrayList<DirectedEdge>();
		}
	}
	public EdgeWeightedDigraph(In in){
		 this(in.readInt());
	        int E = in.readInt();
	        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
	        for (int i = 0; i < E; i++) {
	            int v = in.readInt();
	            int w = in.readInt();
	            double weight = in.readDouble();
	            addEdge(new DirectedEdge(v, w, weight));
	        }
	}
	public long V(){
		return V;
	}
	public long E(){
		return E;
	}
	public void addEdge(DirectedEdge e){
		adj[(int)e.from()].add(e);
		E++;
	}
	public Iterable<DirectedEdge> adj(long v){
		return adj[(int)v];
	}
	public Iterable<DirectedEdge> edges(){
		ArrayList<DirectedEdge> list = new ArrayList<DirectedEdge>();
		for(int v =0;v<V;v++){
			for(DirectedEdge e: adj[v]){
				list.add(e);
			}
		}
		return list;
		
	}
	

}
