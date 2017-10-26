package fordfulkerson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.In;

public class FordFulkersonDemo {
	private int[][] Graph;
	private int[][] f;
	private int[][] r;
	private int[] parent;
	private int source;
	private int terminal;
	
	public FordFulkersonDemo(int Num){
		Graph = new int[Num][Num];
		f = new int[Num][Num];
		r = new int[Num][Num];
		parent = new int[Num];
	}
	public FordFulkersonDemo(In in){
		this(in.readInt());
		for(int i = 0; i < parent.length; i++){
			for(int j = 0; j < parent.length; j++){
				Graph[i][j] = in.readInt();
			}
		}
		source = in.readInt();
		terminal = in.readInt();
	}
	private void initialization(){
		for(int i = 0; i < parent.length; i++){
			Arrays.fill(f[i], 0);
		}
	}
	
	private int[][] residualNetwork(int[][] graph,int[][] f){
		int[][] r = new int[parent.length][parent.length];
		for(int i = 0; i < parent.length; i++){
			for(int j = 0; j < parent.length; j++){
				r[i][j] = graph[i][j] - f[i][j];
			}
		}
		return r;
	}
	
	private int argumentPath(int[][] r,int s,int t){
		Arrays.fill(parent, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		int maxFlow = Integer.MAX_VALUE;
		q.add(s);
		parent[s] = s;
		while(!q.isEmpty()){
			int v = q.poll();
			if(v == t){
				while(v != s){
					if(maxFlow > r[parent[v]][v])
						maxFlow = r[parent[v]][v];
					v = parent[v];
				}
				break;
			}
				for(int i = 0 ; i < parent.length; i++){
					if(i != v && parent[i] == -1 && r[v][i] > 0){
						parent[i] = v;
					    q.add(i);
					}
				}
			}
			
		if(parent[t] == -1){
			maxFlow = -1;
		}
		return maxFlow;
	}
	
	public int run(){
		int sum = 0;
		initialization();
		r = residualNetwork(Graph,f);
		int result = argumentPath(r,source,terminal);
		//int cur = terminal;
		while(result != -1){
			int cur = terminal;
			while(cur != source){
				f[parent[cur]][cur] += result;
				f[cur][parent[cur]] = -f[parent[cur]][cur];
				r[parent[cur]][cur] -= result;
				r[cur][parent[cur]] += result;
				cur = parent[cur];
			}
				sum += result;
			//	r = residualNetwork(Graph,f);
				result = argumentPath(r,source,terminal);
		}
		for (int i = 0; i < parent.length; i++) {
	        for (int j = 0; j < parent.length; j++)
	            System.out.printf("%2d  ", f[i][j]);
	        System.out.printf("\n");
	    }
		return sum;
	}
	
	public static void main(String[] args){
		In in = new In(args[0]);
		FordFulkersonDemo ford = new FordFulkersonDemo(in);
		System.out.println("The Max Flow is :" + ford.run());
	}
	

}
