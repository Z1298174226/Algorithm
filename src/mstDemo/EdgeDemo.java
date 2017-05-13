package mstDemo;

public class EdgeDemo implements Comparable<EdgeDemo>{
	private int v;
	private int w;
	private double weight;
	
	public EdgeDemo(int v,int w,double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either(){
		return v;
	}
	
	public int other(int vertex){
		if(vertex == v) return w;
		else if(vertex == w) return v;
		else throw new IllegalArgumentException("The vertex is not exist!");
	}
	
	public double weight (){
		return weight;
	}
	
	public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
	
	
	@Override
	public int compareTo(EdgeDemo e) {
		if(this.weight < e.weight) return -1;
		else if (this.weight > e.weight) return 1;
		else return 0;
	}
	

}
