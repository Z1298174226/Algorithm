package threadDispatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class ThreadDeadLine {
	public static void main(String [] args){
		String filename = args[0];
		try{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		Map<Integer,Double> map = new HashMap<Integer,Double>();
		int n = Integer.parseInt(in.readLine());
		int N = (n<<1) + 2;
		int s= (n<<1); int t = (n<<1) + 1;
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(N);
		for(int i = 0;i < n;i++){
			String arg[] = in.readLine().split("\\s+");
			double duration = Double.parseDouble(arg[1]);
			double time = Double.parseDouble(arg[2]);
			int slot = Integer.parseInt(arg[0]);
			map.put(slot, duration);
			G.addEdge(new DirectedEdge(i,i+n,time));
			G.addEdge(new DirectedEdge(s,i,0.0));
			G.addEdge(new DirectedEdge(i+n,t,0.0));		
		}
		for(int i = 0;i < n;i++){
			for(int j:map.keySet()){
				double timeLate = map.get(i) - map.get(j);
				if(timeLate > 0)
					G.addEdge(new DirectedEdge(i, j, -timeLate));
			}	
		}
		AcyclicSP sp = new AcyclicSP(G,s);
		for(int i = 0;i < n;i++){
			System.out.print(i+ " : ");
			for(DirectedEdge e:G.adj(i)){
				if(e.to()==i+n)
					continue;
				else
				    System.out.print(String.format("%4d",e.to()));
			}
			System.out.println();
		}
		
		}catch(IOException ex){
			System.err.println("Can not Read!!!");
		}
	}

}
