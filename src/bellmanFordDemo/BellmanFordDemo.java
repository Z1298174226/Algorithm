package bellmanFordDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;


public class BellmanFordDemo {
	private WeightedEdge[] edgeTo;
	private Double[] distTo;
	private boolean[] onQueue;
	private Queue<Integer> queue;
	private Iterable<WeightedEdge> cycle;
    private int cnt;
    
	public BellmanFordDemo(WeightedDigraph G,int s){
    	edgeTo = new WeightedEdge[G.V()];
    	distTo = new Double[G.V()];
    	onQueue = new boolean[G.V()];
    	queue = new Queue<Integer>();
    	cnt = 0;
    	
    	for(int i = 0;i < G.V();i++){
    		distTo[i] = Double.POSITIVE_INFINITY;
    	}
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQueue[s] = true;
        
        while(!queue.isEmpty() && !hasNegativeCycle()){
        	int v = queue.dequeue();
        	onQueue[v] = false;
        	relax(G,v);
        	
        }
        
    }
	
	public boolean hasNegativeCycle() {
		
		return cycle != null;
	}

	public void relax(WeightedDigraph G,int v){
		for(WeightedEdge e:G.adj(v)){
			int w = e.to();
		if(distTo[w] > distTo[v] + e.weight()){
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
	       if(!onQueue[w]) {
			queue.enqueue(w);
			onQueue[w] = true;
		   }
		
		}
		if(cnt++ % G.V() == 0){
			findNegativeCycle();
		if(hasNegativeCycle()) return;
		}
   	}
		
	}

	private void findNegativeCycle() {
	    int num = edgeTo.length;
	    WeightedDigraph wg = new WeightedDigraph(num);
	    for(int i = 0;i < num;i++){
	    	if(edgeTo[i] != null)
	    		wg.addEdge(edgeTo[i]);
	    }
	    WeightedNegativeCycle find = new WeightedNegativeCycle(wg);
	    cycle = find.cycle();
	    		
		
	}
	
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	public double distTo(int v){
		 if (hasNegativeCycle())
	            throw new UnsupportedOperationException("Negative cost cycle exists");
		return distTo[v];
	}
	
//	public Iterable<WeightedEdge> pathTo(int v) {
//      
//       
//        if (!hasPathTo(v)) return null;
//        Stack<WeightedEdge> path = new Stack<WeightedEdge>();
//        for (WeightedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()]){
//        	path.push(e);
//        }
//        return path;
//    }
//	
	public Iterable<WeightedEdge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		return new Iterable<WeightedEdge>(){

			@Override
			public Iterator<WeightedEdge> iterator() {
				 List<WeightedEdge> list = new ArrayList<WeightedEdge>();
                 for(WeightedEdge e = edgeTo[v];e != null;e = edgeTo[e.from()]){
                 	list.add(e);
                 }
               
				return new Iterator<WeightedEdge>(){
					private int current = list.size() - 1;
					@Override
					public boolean hasNext() {
						return current > -1;
					}

					@Override
					public WeightedEdge next() {
					   return list.get(current--);
					}
					@Override
					public void remove(){
					
					}
					
				};
			}
			
		};
	}
	
	public static void main(String[] args){
		String filename = args[0];
		In in = new In(filename);
		int s = Integer.parseInt(args[1]);
		WeightedDigraph G = new WeightedDigraph(in);
		BellmanFordDemo bell = new BellmanFordDemo(G,s);
		if(bell.hasNegativeCycle()){
			for(WeightedEdge e:bell.cycle){
				System.out.println(e);
			}
		}
		else{
			for(int i = 0;i<G.V();i++){
				if(bell.hasPathTo(i)){
					System.out.print(String.format("%d --> %d (%5.2f): ", s,i,bell.distTo[i]));
					for(WeightedEdge e:bell.pathTo(i)){
						System.out.print(e + "  ");
					}
					System.out.println();
				}
				else{
					System.out.println(String.format("%d ---> %d  no path!", s,i));
				}
			}
			}
	}
}
