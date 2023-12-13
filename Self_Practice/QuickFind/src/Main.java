public class Main {
    public int[] id;

    public Main(int N){
        id = new int[N];
        for(int i=0; i<N; i++)
            id[i] = i;
    }
    public int find(int p){
        return id[p];
    }

    public void union(int p, int q){
        int pid = id[p], qid = id[q];
        for(int i=0; i<id.length; i++){
            if(id[i] == pid) id[i] = qid;
        }
    }
    public static void main(String[] args) {
        Main m = new Main(10);
        m.union(2,3);
        m.union(1, 2);
        System.out.println(m.id[1]+" "+m.id[2]);
    }
}