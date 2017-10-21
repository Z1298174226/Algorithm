package sortDemo;

/**
 * Created by qtfs on 2017/10/13.
 */
public class QuickUnion {
    private int num;
    private int[] parent = null;
    public QuickUnion(int num) {
        this.num = num;
        parent = new int[num];
        for(int i = 0; i < num; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        while(p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return ;
        parent[rootP] = rootQ;
    }
}
