import edu.princeton.cs.algs4.In;

/**
 * Created by qtfs on 2017/10/16.
 */
public class RedundantConnection {
    int[] parents = new int[2001];
    int[] degreeParent = new int[2001];
    int[] degreeSon = new int[2001];

    public int[] findRedundantDirectedConnection(int[][] edges) {
        for(int i = 0; i < parents.length; i++)
            parents[i] = i;
        for(int[] edge : edges) {
            degreeParent[edge[0]]++;
            degreeSon[edge[1]]++;
        }
        for(int[] edge : edges) {
            int parent = edge[0];
            int son = edge[1];
            if(find(parent) == find(son)) {
                while(degreeParent[son] == 1 && degreeSon[son] == 1) {
                    son = parent;
                    parent = parents[son];
                }
                return new int[]{parent, son};
            }
            else union(son, parent);
        }
        return new int[0];
    }

    private int find(int f) {
        while(f != parents[f]) {
            parents[f] = parents[parents[f]];
            f = parents[f];
        }
        return f;
    }

    private void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        parents[rootP] = rootQ;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int num = in.readInt();
        int[][] edges = new int[num][2];
        for(int i = 0; i < num; i++) {
            edges[i][0] = in.readInt();
            edges[i][1] = in.readInt();
        }

        RedundantConnection re = new RedundantConnection();

        for(int i : re.findRedundantDirectedConnection(edges)) {
            System.out.println(i);
        }
    }

}
