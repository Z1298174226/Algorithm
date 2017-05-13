package bellmanFord;

public interface WeightedDigraphImplement {
	
	public void addEdge(Edge e);
	public Iterable<Edge> adj(int v);
	public Iterable<Edge> edges();
	public int V();
	public int E();

}
