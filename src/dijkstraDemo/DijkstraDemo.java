package dijkstraDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DijkstraDemo {
	
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private SimplePriorityQueue<Double> pq;
	
	public DijkstraDemo(EdgeWeightedDigraph G , long s){
		distTo = new double[(int)G.V()];
		edgeTo = new DirectedEdge[(int)G.V()];
		pq = new SimplePriorityQueue<Double>((int)G.V());
		validVertex(s);
		for(int v = 0;v<G.V();v++){
			distTo[v] = Double.POSITIVE_INFINITY;			
		}
		distTo[(int)s] = 0.0;
		
		pq.insert((int)s,distTo[(int)s]);
		while(!pq.isEmpty()){
			long v = pq.delMin();
			for(DirectedEdge e: G.adj(v)){
				relax(e);
			}
		}
		assert check();
	}

	private boolean check() {
		// TODO Auto-generated method stub
		return false;
	}

	private void relax(DirectedEdge e) {
		long v = e.from();
		long w = e.to();
		if( distTo[(int)w] > distTo[(int)v] + e.weight()){
			distTo[(int)w] = distTo[(int)v] + e.weight();
			edgeTo[(int)w] = e;
			if (pq.contains((int)w)) pq.decreaseKey((int)w, distTo[(int)w]);
            else                pq.insert((int)w, distTo[(int)w]);
		}
		
		
	}
	
	public double distTo(long v){
		validVertex(v);
		return distTo[(int)v];
	}
	public boolean haspathTo(long v){
		validVertex(v);
		return distTo[(int)v] < Double.POSITIVE_INFINITY;
	}
	
	public Iterable<DirectedEdge> pathTo(int v){
		return new Iterable<DirectedEdge>(){

			@Override
			public Iterator<DirectedEdge> iterator() {
				List<DirectedEdge> path = new ArrayList<DirectedEdge>();
				for(DirectedEdge e = edgeTo[v] ; e!=null; e = edgeTo[(int)e.from()]){
					path.add(e);
				}
				return new Iterator<DirectedEdge>(){
                    int current = path.size() - 1;
					@Override
					public boolean hasNext() {
						return current > -1;
					}

					@Override
					public DirectedEdge next() {
						return path.get(current);
					}
					@Override
					public void remove(){
						throw new UnsupportedOperationException();
					}
					
				};
			}
			
		};
	}

	private void validVertex(long s) {
		if(s<0||s>distTo.length)
			throw new IllegalArgumentException("The Source Vertex is Error!");
		
	}
	public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        long s = Integer.parseInt(args[1]);

        // compute shortest paths
        DijkstraDemo sp = new DijkstraDemo(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.haspathTo(t)) {
                StdOut.printf("%d to %d (%.2f):  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }

}
