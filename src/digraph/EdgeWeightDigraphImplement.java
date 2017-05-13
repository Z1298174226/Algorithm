package digraph;

public interface EdgeWeightDigraphImplement {
   void addEdge(DirectedEdge e);
   Iterable<DirectedEdge> adj(int v);
   int V();
   int E();
   Iterable<DirectedEdge> edges();
   String toString();
}
