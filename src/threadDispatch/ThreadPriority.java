package threadDispatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import dijkstra.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdOut;



public class ThreadPriority {
	public static void main(String[] args){
		String filename = args[0];
	
		try{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		int n = Integer.parseInt(in.readLine());
		int N = (n<<1) + 2;
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(N);
		int s = (n<<1); int t = (n<<1) + 1;
		for(int i = 0; i < n; i++){
			String arg[] = in.readLine().split("\\s+");
			double duration = Double.parseDouble(arg[0]);
			G.addEdge(new DirectedEdge(i, i+n , duration));
			G.addEdge(new DirectedEdge(s, i, 0.0));
			G.addEdge(new DirectedEdge(i+n, t, 0.0));
			for(int j = 1; j<arg.length; j++){
				G.addEdge(new DirectedEdge(i+n,Integer.parseInt(arg[j]), 0.0));
			}
			
		}
		
		AcyclicSP sp = new AcyclicSP(G,s); 
		for(int i =0; i<n;i++){
			System.out.println(String.format("%4d: %5.1f", i,sp.distTo(i)));
		}
		System.out.println(String.format("Finish time: %5.1f", sp.distTo(t)));
	    
		 for (int v = 0; v < G.V(); v++) {
	            if (sp.hasPathTo(v)) {
	                StdOut.printf("%d to %d (%.2f)  ",s, v, sp.distTo(v));
	                for (DirectedEdge e : sp.pathTo(v)) {
	                    StdOut.print(e + "   ");
	                }
	                StdOut.println();
	            }
	            else {
	                StdOut.printf("%d to %d         no path\n", s, v);
	            }
	         }
	  }catch(IOException ex){
		  System.err.println("Can not read");
	  }
		
	}

}
