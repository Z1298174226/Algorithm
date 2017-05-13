package digraph;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;

public class EdgeWeightDigraph implements EdgeWeightDigraphImplement{
	
	private int V;
	private int E;
	private List<DirectedEdge>[] adj;
	@SuppressWarnings("unchecked")
    public EdgeWeightDigraph(int V){
    	this.V = V;
    	this.E = 0;
    	adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
    	for(int v= 0;v<V;v++)
    		adj[v] = new ArrayList<DirectedEdge>();
    	
    }
	public EdgeWeightDigraph(In in) {
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

	@Override
	public void addEdge(DirectedEdge e) {
		adj[e.from()].add(e);
		E++;
		
	}

	@Override
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	@Override
	public int V() {
	return V;
	}

	@Override
	public int E() {
		return E;
	}

	@Override
	public Iterable<DirectedEdge> edges() {
		ArrayList<DirectedEdge> list = new ArrayList<DirectedEdge>();
		for(int v = 0;v<V;v++){
			for(DirectedEdge e:adj[v]){
				list.add(e);
			}
		}
		return list;
	}

}
