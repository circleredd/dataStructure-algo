public class MergeBU {
    private static Comparable[] a;
//    MergeBU(Comparable[] arr){
//        a = arr;
//    }
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi, int sz){
        for(int i=lo; i<=hi; i++){
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for(int k = lo; k<=hi; k++){
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(a[j].compareTo(a[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        System.out.println("SZ = "+sz);
        for(int k = lo; k<=hi; k++){System.out.print(a[k]+" ");}
        System.out.println();
    }
    private static void sort(Comparable[] a){
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for(int sz = 1; sz<N; sz = sz+sz){
            for(int lo = 0; lo < N-sz; lo += sz+sz){
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1), sz);
            }
        }
//        for(int i=0; i<a.length; i++){
//            System.out.println(a[i]);
//        }
    }
    public static void main(String[] args) {
        Comparable[] a = {'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E', 'W', 'Z'};
        MergeBU m = new MergeBU();
        m.sort(a);
//        System.out.println(a[0]+" "+a[1]+"->"+a[0].compareTo(a[1]));

        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }
}
