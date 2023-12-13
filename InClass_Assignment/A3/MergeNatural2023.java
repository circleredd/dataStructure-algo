
//todo: modify sort and findNextRun functions to implement a mergesort that finds natural ordered items 
//with "runs" and merges them using a bottom up approach.   
//DO NOT EDIT other functions NOR add global variables.


import javax.swing.plaf.IconUIResource;

//MergeNatural2023 is modified from MergeBU from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/MergeBU.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MergeBU.html
public class MergeNatural2023 {

    // This class should not be instantiated.
    private MergeNatural2023() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unnecessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }
    
    // todo: write code in this function
    // finds next run in the input array with the given starting index
    // returns end index of the run
    public int findNextRun(Comparable[]a, int startIndex) {
    	int endIndex = a.length -1;
    	for(int i=startIndex; i<a.length-1; i++){   //找出下一個比上一個大的位置，並回傳此位置作為該區段的end_index
            if(less(a[i+1], a[i])) {
                endIndex = i;
                break;
            }
        }

    	System.out.println("Run start: " + startIndex + ", end: " + endIndex);
    	show(a,startIndex,endIndex);
    	return endIndex;
    }
    
    // todo: write code in this function to sort the array with mergesort using natural runs
    // use findNextRun function in this function to identify runs
    public static void sort(Comparable[] a) {
        // add your code here
        int[] end_index = new int[a.length];   //用來存取後面所有區段的end_index
        MergeNatural2023 m = new MergeNatural2023();
        while(!isSorted(a)) {   //若a[]還沒排序好，while不斷進行
            int j = 0;  //用來當end_index[]的索引
            int count_index = 0;    //計算這輪while總共有幾個區段
            for (int i = 0; i < a.length; i++) {    //印出所有區段的end_index並存入end_index[]
                i = m.findNextRun(a, i);
                end_index[j++] = i;
                count_index++;  //計算這輪while總共有幾個區段
            }
            Integer[] aux = new Integer[a.length];      //新建一個aux[]用來傳入merge()
            merge(a, aux, 0, end_index[0] , end_index[1]);  //merge前面兩個區段
            if(count_index > 3) {       //如果有至少四個區段，,merge第三個和第四個區段
                merge(a, aux, end_index[1] + 1, end_index[2], end_index[3]);
            }
            if(isSorted(a))
                m.findNextRun(a, 0); //因為若a[]已排好就不會再進入while迴圈，所以這邊排好之後再確認一次最終區段
        }

    }

  /***********************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    // print array between indices to standard output
    private static void show(Comparable[] a, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Integer[] a = {1,2,3,9,12,20,7,15,11,6,20,300,0};
        MergeNatural2023.sort(a);
        
        System.out.println();
        System.out.print("Sorted: ");
        show(a); 
    }
}
