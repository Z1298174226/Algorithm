package digraph;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by qtfs on 2017/12/6.
 */
public class DepthFirstOrder {
    int[] pre;
    int[] post;
    List<Integer> preQueue;
    List<Integer> postQueue;
    boolean[] marked;
    int preCount;
    int postCount;

    public DepthFirstOrder(EdgeWeightedGraph G) {
        pre = new int[G.getV()];
        post = new int[G.getV()];
        marked = new boolean[G.getV()];
        preQueue = new ArrayList<Integer>();
        postQueue = new ArrayList<Integer>();

        for(int i = 0; i < G.getV(); i++) {
            if(!marked[i])
                dfs(G, i);
        }
    }

    private void dfs(EdgeWeightedGraph G, int vertex) {
        marked[vertex] = true;
        pre[vertex] = preCount++;
        preQueue.add(vertex);
        Iterator<DirectedEdge> i = G.adj(vertex).iterator();
        while(i.hasNext()) {
            int w = i.next().to();
            if(!marked[w])
                dfs(G, w);
        }
        post[vertex] = postCount++;
        postQueue.add(vertex);
    }

    public int pre(int vertex) {
        return pre[vertex];
    }

    public int post(int vertex) {
        return post[vertex];
    }

    public Iterable<Integer> postOrder() {
        return postQueue;
    }

    public Iterable<Integer> preOrder() {
        return preQueue;
    }

    public Iterable<Integer> reservePostOrder() {
        return new Iterable<Integer>() {
            @NotNull
            @Override
            public Iterator<Integer> iterator() {
               return new Iterator<Integer>() {
                   int current = postQueue.size() - 1;
                   @Override
                   public boolean hasNext() {
                       return current > -1;
                   }

                   @Override
                   public Integer next() {
                       return postQueue.get(current--);
                   }
               };
            }
        };
    }

    public Iterable<Integer> reservePreOrder() {
        return new Iterable<Integer>() {
            @NotNull
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    int current = preQueue.size() - 1;
                    @Override
                    public boolean hasNext() {
                        return current > -1;
                    }

                    @Override
                    public Integer next() {
                        return preQueue.get(current--);
                    }
                };
            }
        };
    }
}
