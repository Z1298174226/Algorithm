package sortDemo;

/**
 * Created by qtfs on 2017/10/13.
 */
public class UnionFind {

    private int num;
    private int[] parent = null;
    private int[] rank = null;

    public UnionFind(int num) {
        this.num = num;
        parent = new int[num];
        rank = new int[num];
        for(int i = 0; i < num; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        while(p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p , int q) {
        return find(p) == find(q);
    }

    public void union(int p , int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        if(rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        }
        else {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
    }
}
