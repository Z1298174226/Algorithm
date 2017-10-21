import edu.princeton.cs.algs4.In;

import javax.naming.InitialContext;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by qtfs on 2017/10/18.
 */
public class FordFullerson {
    private int[][] graph = null;
    private int[][] r = null;
    private int[][] f = null;
    private int[] parents = null;
    private int source;
    private int terminal;
    public FordFullerson(int num) {
        graph = new int[num][num];
        r = new int[num][num];
        f = new int[num][num];
        parents = new int[num];
    }

    public void Initialization(In in) {
        for(int i = 0; i < parents.length ;i++)
            Arrays.fill(f[i], 0);
        int len = parents.length;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                graph[i][j] = in.readInt();
            }
        }
        source = in.readInt();
        terminal = in.readInt();
    }

    public int[][] residualNetwork(int[][] graph, int[][] f) {
        for(int i = 0; i < parents.length; i++) {
            for(int j = 0; j < parents.length; j++) {
                r[i][j] = graph[i][j] - f[i][j];
            }
        }
        return r;
    }

    public int augamentPath(int[][] r, int source, int terminal) {
        int result = Integer.MAX_VALUE;
        Arrays.fill(parents, -1);
        Queue<Integer> queue = new LinkedList<Integer>();
        parents[source] = source;
        queue.add(source);
        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            if(vertex == terminal) {
                while(vertex != source) {
                    if(result > r[parents[vertex]][vertex])
                        result = r[parents[vertex]][vertex];
                    vertex = parents[vertex];
                }
            }
            else {
                for(int i = 0; i < parents.length; i++) {
                    if(i != vertex && parents[i] == -1 && r[vertex][i] > 0) {
                        parents[i] = vertex;
                        queue.add(i);
                    }

                }
            }
        }
        if(parents[terminal] == -1)
            result = -1;
        return result;
    }

    public int compute() {
        int sum = 0;
        int result = 0;
        r = residualNetwork(graph, f);
        result = augamentPath(r, source, terminal);
        while(result != -1) {
            sum += result;
            int cur = terminal;
            while(cur != source) {
                f[parents[cur]][cur] += result;
                f[cur][parents[cur]] = -f[parents[cur]][cur];
                r[parents[cur]][cur] -= result;
                r[cur][parents[cur]] += result;
                cur = parents[cur];
            }
            result = augamentPath(r, source, terminal);
        }
        return sum;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int num = in.readInt();
        FordFullerson fordFullerson = new FordFullerson(num);
        fordFullerson.Initialization(in);
        System.out.println(fordFullerson.compute());
    }

}
