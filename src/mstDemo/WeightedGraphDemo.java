package mstDemo;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import mst.Edge;

public class WeightedGraphDemo {
	private int V;
	private int E;
	private List<EdgeDemo>[] adj;
	
	@SuppressWarnings("unchecked")
	public WeightedGraphDemo(int vNum){
		this.V = vNum;
		adj = (List<EdgeDemo>[]) new ArrayList[V];
		for(int v = 0;v<V;v++){
			adj[v] = new ArrayList<EdgeDemo>();
		}
	}
	public WeightedGraphDemo(In in){
		this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validVertex(v);
            validVertex(w);
            double weight = in.readDouble();
           addEdge(new EdgeDemo(v,w,weight));
        }
		
	}
	
	public Iterable<EdgeDemo> adj(int vertex){
		validVertex(vertex);
		return adj[vertex];
	
	}
	public void addEdge(EdgeDemo e){
		int v = e.either() ; int w = e.other(v);
		validVertex(v);
		validVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	private void validVertex(int vertex) {
		if(vertex<0 || vertex > V)
			throw new IllegalArgumentException("The vertex is Error!");
	}
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}

}
