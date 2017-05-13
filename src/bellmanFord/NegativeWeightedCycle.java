package bellmanFord;

import edu.princeton.cs.algs4.Stack;

public class NegativeWeightedCycle {
	
	private Edge[] edgeTo;
	private boolean[] onStack;
	private boolean[] marked;
	private Stack<Edge> cycle;
	public NegativeWeightedCycle(WeightedDigraph G){
		onStack = new boolean[G.V()];
		marked = new boolean[G.V()];
		edgeTo = new Edge[G.V()];
		for(int i = 0; i < G.V(); i++){
			if(!marked[i]) dft(G,i);
		}
		
	}
	private void dft(WeightedDigraph G, int i) {
	    marked[i] = true;
	    onStack[i] = true;
	    for(Edge e:G.adj(i)){
	    	int w = e.to();
	    	if(cycle != null) return;
	    	if(!marked[w]){
	    		edgeTo[w] = e;
	    		dft(G,w);
	    	}
	    	if(onStack[w]){
	    		Edge f = e;
	    		while(w != f.from()){
	    			cycle = new Stack<Edge>();
	    			cycle.push(f);
	    			f = edgeTo[f.from()];    			
	    		}
	    		cycle.push(f);
	    		return;
	    	}
	    }
	    onStack[i] = false;		
	}
	public Iterable<Edge> cycle(){
		return cycle;
	}
	public boolean hasNegative(){
		if(cycle != null)
			return true;
		else
			return false;
	}

}
