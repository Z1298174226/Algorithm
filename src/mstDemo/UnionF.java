package mstDemo;

import java.util.Arrays;

public class UnionF {
	private int[] parent;
	private int[] rank;
	private int count;
	public UnionF(WeightedGraphDemo G){
		parent = new int[G.V()];
		rank = new int[G.V()];
		count = G.V();
		Arrays.fill(rank, 0);
		for(int i = 0; i < G.V(); i++){
			parent[i] = i;
		}
	}
	
	public int find(int p){
		while(parent[p] != p){
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}
	
	public boolean connected(int p , int q){
	    return find(p) == find(q);
	}
	
	public void union(int p, int q){
		int rootp = find(p);
		int rootq = find(q);
		if(rootp == rootq) return;
		if(rank[rootp] > rank[rootq]){
			parent[rootp] = rootq;  
		}
		else if(rank[rootp] < rank[rootq]){
			parent[rootq] = rootp;
		}
		else{
			parent[rootq] = rootp;
			rank[rootp]++;
		}
		count--;
	}

}
