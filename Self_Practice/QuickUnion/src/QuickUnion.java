public class QuickUnion {
    public int[] id;

    public QuickUnion(int N){
        id = new int[N];
        for(int i=0; i<N; i++)
            id[i] = i;
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
        if(!connected(p, q))
            id[pid] = qid;
    }
    public static void main(String[] args) {
        QuickUnion m = new QuickUnion(10);
        m.union(2,3);
        m.union(2,5);
        m.union(1, 3);
        System.out.println(m.id[1]+" "+m.id[2]);
    }
}