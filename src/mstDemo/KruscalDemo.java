package mstDemo;

import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.IndexMaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class KruscalDemo {
	private Queue<EdgeDemo> queue = new Queue<EdgeDemo>();
	private MinPQ<EdgeDemo> pq = new MinPQ<EdgeDemo>();
	private double weight = 0;
	public KruscalDemo(WeightedGraphDemo G){
		UnionF uf = new UnionF(G);
		for(EdgeDemo e: G.edges()){
			pq.insert(e);
		}
		
		while(!pq.isEmpty()){
			EdgeDemo e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(!uf.connected(v, w)){
				uf.union(v, w);
				queue.enqueue(e);
				weight += e.weight();
			}
		}
		
	}
	public Iterable<EdgeDemo> edges(){
		return queue;
	}
	
	public static void main(String[] args){
		In in = new In(args[0]);
		WeightedGraphDemo graph = new WeightedGraphDemo(in);
		KruscalDemo kruscal = new KruscalDemo(graph);
		for(EdgeDemo e: kruscal.edges()){
			System.out.println(e);
		}
		System.out.println(kruscal.weight);
	}

}
