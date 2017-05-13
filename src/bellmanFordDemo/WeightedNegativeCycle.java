package bellmanFordDemo;

import edu.princeton.cs.algs4.Stack;

public class WeightedNegativeCycle {
    private boolean[] onStack;
    private boolean[] marked;
    private Stack<WeightedEdge> cycle;
    private WeightedEdge[] edgeTo;
    
    public WeightedNegativeCycle(WeightedDigraph G){
    	onStack = new boolean[G.V()];
    	marked = new boolean[G.V()];
    	edgeTo = new WeightedEdge[G.V()];
    	for(int i = 0;i < G.V();i++){
    		if(!marked[i]) dft(G,i);
    	}
    }
    
    private void dft(WeightedDigraph G,int v) {
		marked[v] = true;
		onStack[v] = true;
		for(WeightedEdge e:G.adj(v)){
			int w = e.to();
			if(cycle != null) return;
			else if(!marked[w]){
				edgeTo[w] = e;
				dft(G,w);
			}
			else if(onStack[w]){
				cycle = new Stack<WeightedEdge>();
				WeightedEdge f = e;
				while(w != f.from()){
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				cycle.push(f);
				return;
			}
		}
		onStack[v] = false;
		
	}

	public boolean hasNegative(){
    	return cycle != null;
    }
    public Iterable<WeightedEdge> cycle(){
    	return cycle;
    }
}
