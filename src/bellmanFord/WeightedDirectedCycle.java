package bellmanFord;

import edu.princeton.cs.algs4.Stack;

public class WeightedDirectedCycle {
	private boolean[] onStack;
	private boolean[] marked;
	private Edge[] edgeTo;
	private Stack<Edge> cycle;
	public WeightedDirectedCycle(WeightedDigraph G,int v){
		onStack = new boolean[G.V()];
		marked = new boolean[G.V()];
		edgeTo = new Edge[G.V()];
		cycle = new Stack<Edge>();
		while(!marked[v]){
			dft(G,v);
		}
		
	}
	
	private void dft(WeightedDigraph G,int v){
		onStack[v] = true;
		marked[v] = true;
		for(Edge e:G.adj(v)){
			int w = e.to();
			if(cycle != null) return;
			else if(!marked[w]){
				edgeTo[w] = e;
				dft(G,w);
			}
			else if(onStack[w]){
				Edge t = e;
				while(w != t.from()){
					cycle.push(t);
					t = edgeTo[e.from()];
				}
				cycle.push(t);
				return;
			}
		}
		onStack[v] = false;
		
	}
	
//	private void dft(WeightedDigraph G,int v){
//		onStack[v] = true;
//		marked[v] = true;
//		for(Edge e:G.adj(v)){
//			int w = e.to();
//			if(cycle != null) return;
//			else if(!marked[w]){
//			
//				edgeTo[w] = e;
//				dft(G,w);
//			}
//			else if(onStack[w]){
//				Edge t = e;
//				while(w != t.from()){
//					cycle.push(t);
//					t = edgeTo[t.from()];
//				}
//				cycle.push(t);
//				return;
//			}
//			
//		}
//		onStack[v] = false;
//	}

}

