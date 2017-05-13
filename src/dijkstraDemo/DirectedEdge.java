package dijkstraDemo;

public class DirectedEdge implements DirectedEdgeImplement {
	
	private long v;
	private long w;
	private double weight;
	public DirectedEdge(long v,long w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	@Override
	public long from(){
		return v;
	}
	@Override
	public long to(){
		return w;
	}
	@Override
	public double weight(){
		return weight;
	}
	@Override
	public String toString(){
		return String.format("%d -> %d %.2f",v,w,weight );
	}
	

}
