import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by qtfs on 2017/12/5.
 */
public class FordFulkerson {
    private int length;
    private int[] parents;
    private int[][] graph ;
    private int source;
    private int terminal;
    private int[][] r ;
    private int[][] f ;

    public FordFulkerson(int length) {
        this.length = length;
        r = new int[length][length];
        graph = new int[length][length];
        parents = new int[length];
        f = new int[length][length];
    }

    public FordFulkerson(In in) {
        this(in.readInt());
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++)
                graph[i][j] = in.readInt();
        }
        source = in.readInt();
        terminal = in.readInt();
    }

    public void initGraph() {
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++)
                r[i][j] = graph[i][j];
        }
    }

//    public int[][] residual(int[][] graph, int[][] f) {
//        for(int i = 0; i < length; i++) {
//            for(int j = 0; j < length; j++)
//                r[i][j] = graph[i][j];
//        }
//        return r;
//    }

    public int augumentPath(int[][] r, int source, int terminal) {
        Arrays.fill(parents, -1);
        int result = Integer.MAX_VALUE;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        while(!queue.isEmpty()) {
            int v = queue.poll();
            if(v == terminal) {
                while(v != source) {
                    result = Math.min(result, r[parents[v]][v]);
                    v = parents[v];
                }
            }
            else {
                for(int i = 0; i < length; i++) {
                    if(i != v && r[v][i] > 0 && parents[i] == -1) {
                        parents[i] = v;
                        queue.add(i);
                    }
                }
            }
        }
        if(parents[terminal] == - 1)
            return -1;
        return result;
    }

    public int findMaxFlow() {
        initGraph();
      //  r = residual(graph, f);
        int sum = 0;
        int result = augumentPath(r, source, terminal);
        while(result != -1) {
            int vertex = terminal;
            while(vertex != source) {
                f[parents[vertex]][vertex] += result;
                f[vertex][parents[vertex]] -= result;
                r[parents[vertex]][vertex] -= result;
                r[vertex][parents[vertex]] += result;
                vertex = parents[vertex];
            }
            sum += result;
            result = augumentPath(r, source, terminal);
        }
        return sum;
    }
    public static void main(String[] args) {
        In in = new In("src\\graphFile\\maxFlow3.txt");
        FordFulkerson f = new FordFulkerson(in);
        System.out.println(f.findMaxFlow());

    }
}
