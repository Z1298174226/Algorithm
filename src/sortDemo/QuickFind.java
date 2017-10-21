package sortDemo;

/**
 * Created by qtfs on 2017/10/13.
 */
public class QuickFind {

    private int num = 0;
    int[] id = null;
    public QuickFind(int num) {
        this.num = num;
        id = new int[num];
        for(int i =0; i < num; i++)
            id[i] = i;
    }
    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = p;
        int rootQ =q;
        for(int i = 0; i < num; i++) {
            if(id[p] == rootP)
                id[p] = rootQ;
        }
    }
}
