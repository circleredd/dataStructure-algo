
public class WeightedQuickUnion {
    public int[] id;
    public int[] sz;
    public WeightedQuickUnion(int N){
        id = new int[N];
        sz = new int[N];
        for(int i=0; i<N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    public int find(int p){
        while(id[p] != p)
            p = id[p];
        return p;
    }

    public boolean connected(int p, int q){
        return  find(p) == find(q);
    }
    public void union(int p, int q){
        int pid = find(p), qid = find(q);
        if(sz[pid] <= sz[qid]){
            id[pid] = qid;
            sz[qid] += sz[pid];
        }
        else{
            id[qid] = pid;
            sz[pid] += sz[qid];
        }
    }
    public static void main(String[] args) {
        WeightedQuickUnion m = new WeightedQuickUnion(10);
        m.union(2,3);
        m.union(2,5);
        m.union(1, 3);
        System.out.println(m.id[1]+" "+m.id[2]);
    }
}