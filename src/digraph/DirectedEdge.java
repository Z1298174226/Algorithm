package digraph;

public class DirectedEdge implements DirectedEdgeImplement{
	
	private  double weight;
	private  int v;
	private int w;
	
   public DirectedEdge(int v ,int w , double weight){
	   this.v = v;
	   this.w = w;
	   this.weight = weight;
   }
	@Override
	public double weight() {
		return weight;
	}

	@Override
	public int from() {
		return v;
	}

	@Override
	public int to() {
		return w;
	}
	
	@Override
	public String toString(){
		return String.format("%d--->%d %.2f", v,w,weight);
	}
	

}
