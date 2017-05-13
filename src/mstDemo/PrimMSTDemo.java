package mstDemo;

import dijkstra.IndexMinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;


public class PrimMSTDemo {
	
	private boolean[] marked;
	private IndexMinPQ<Double> pq;
	private EdgeDemo[] edgeTo;
	private double[] distTo;
	double weight = 0.0;
	int num = 0;
  	
	public PrimMSTDemo(WeightedGraphDemo G){
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		edgeTo = new EdgeDemo[G.V()];
		distTo = new double[G.V()];
		for(int v = 0;v < G.V(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		 distTo[0] = 0.0;
		 pq.add(0, distTo[0]);
		 for (int v = 0; v < G.V(); v++)      // run from each vertex to find
	            if (!marked[v]) prim(G, v);      // minimum spanning forest
	}

	private void prim(WeightedGraphDemo G, int s) {
//		distTo[s] = 0.0;
//		pq.add(s, distTo[s]);
		while(!pq.isEmpty()){
			int v = pq.delMin();
//			int v = pq.poll();
			scan(G,v);
		}
		
	}

	private void scan(WeightedGraphDemo G, int v) {
		marked[v] = true;
		for(EdgeDemo e: G.adj(v)){
			int w = e.other(v);
			if(marked[w]) continue;
			if(e.weight() < distTo[w]){
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if(pq.contains(w)) pq.decreaseKey(w, distTo[w]);
				else pq.add(w, distTo[w]);
			}
		}
		
	}
	public double weight(){
		for (EdgeDemo e : edges())
            weight += e.weight();
        return weight;
	}
	
	public double weightTw(){
		for(double weightTw:distTo){
			weight +=weightTw;
		}
		return weight;
			
	}
	public int num(){
		for(EdgeDemo e : edges())
			num++;
		return num;
	}
	public Iterable<EdgeDemo> edges(){
		Queue<EdgeDemo> queue = new Queue<EdgeDemo>();
		for(int v = 0;v<edgeTo.length;v++){
			EdgeDemo e = edgeTo[v];
			if(e!=null){
			   queue.enqueue(e);
			}
		}
		return queue;
	}
	public static void main(String[] args){
		In in = new In(args[0]);
		WeightedGraphDemo G = new WeightedGraphDemo(in);
		PrimMSTDemo mst = new PrimMSTDemo(G);
		for(EdgeDemo e:mst.edges()){
			System.out.println(e);
		}
		//System.out.println(mst.weight());
		System.out.println(mst.weightTw());
		System.out.println(mst.num());
		
	}

}
