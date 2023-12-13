//todo: student id & name
//todo: write code in the chop(int p, int q) to calculate what happens when a tree branch is chopped. 
//todo: modify union(int p, int q) to maintain add nodes to the tree. 
//DO NOT EDIT other functions NOR add global variables.



public class HW1 {
 
	// ChopTrees is modified from QuickUnionUF https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/QuickUnionUF.java.html
	// QuickUnionUF JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/QuickUnionUF.html
	public static class ChopTrees {
		private int[] parent;   // parent[i] = parent of i
		private int count;      // number of components
		private int N;
		private boolean collapsed;

		/**
		* Initializes an empty union-find data structure with
		* {@code n} elements {@code 0} through {@code n-1}.
		* Initially, each element is in its own set.
		*
		* @param  n the number of elements
		* @throws IllegalArgumentException if {@code n < 0}
		*/
		public ChopTrees(int n) {
			count = n;
			N = n;
			collapsed = false;
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = 0;
			}
		}

		/**
		* Returns the number of sets.
		*
		* @return the number of sets (between {@code 1} and {@code n})
		*/
		public int count() {
			return count;
		}

		/**
		* Returns the canonical element of the set containing element {@code p}.
		*
		* @param  p an element
		* @return the canonical element of the set containing {@code p}
		* @throws IllegalArgumentException unless {@code 0 <= p < n}
		*/
		public int find(int p) {
			validate(p);
			while (parent[p] != 0 && parent[p] != -1)
				p = parent[p];
			return p;
		}

		/**
		* Returns true if the two elements are in the same set.
		* 
		* @param  p one element
		* @param  q the other element
		* @return {@code true} if {@code p} and {@code q} are in the same set;
		*         {@code false} otherwise
		* @throws IllegalArgumentException unless
		*         both {@code 0 <= p < n} and {@code 0 <= q < n}
		* @deprecated Replace with two calls to {@link #find(int)}.
		*/
		@Deprecated
		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		// validate that p is a valid index
		private void validate(int p) {
			int n = parent.length;
			if (p < 0 || p >= n) {
				System.out.println("index " + p + " is not between 0 and " + (n-1));
			}
		}

		/**
		* Adds a branch to the tree, see assignment for details
		*
		* @param  p one element
		* @param  q the other element
		* @throws IllegalArgumentException unless
		*         both {@code 0 <= p < n} and {@code 0 <= q < n}
		*/
		/*public boolean union(int p, int q) {
			// modify code in this method
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) return;
			parent[rootP] = rootQ;
			count--;
			return false;
		}*/
		public boolean union(int p, int q) {
			// modify code in this method
			if((N <= 0 || N > 1000)||(p<0||p>N||q<0||q>N))	return  false; //數量不對直接回傳false
			int rootP = find(p), rootQ = find(q);
			boolean is_under = false; //判斷是否在同一棵樹且p在q的下面(parent)，若為true則不能union
			int tmp = q;
			while (parent[tmp] != 0 && parent[tmp] != -1){//判斷是否在同一棵樹且p在q的下面(parent)，若為true則不能union
				tmp = parent[tmp];
			 	if(p == tmp){
					 is_under = true;
					 break;
				}
			}
			if(!collapsed && !is_under) {
				if (rootP == -1 || rootQ == -1) return false; //該樹已被砍掉
				else{
					parent[p] = q; //p及其上面的節點接到q上
					return true;
				}
			}
			else
				return false;
		}

		// For chopping tree branches, see assignment for details
		public int chop(int p, int q){
			// write your code here
			if(collapsed) return -1;

			int rootP = find(p), rootQ = find(q);
			if(rootP != rootQ) return  0;	//如果p、q不同棵樹或p、q其中一個不在樹上，則chop失敗

			int height_arr[] = new int[N];	//用array儲存各節點高度
			int max_height = 0;		//所有節點高度最高的值
			int tmp = q;

			for(int i=0; i<N; i++) {	//將所有節點的高度存入height_arr
				tmp = i;
				int height = 0;
				while (parent[tmp] != 0 && parent[tmp] != -1) {
					tmp = parent[tmp];
					height += 1;
				}
				height_arr[i] = height;
				if (height > max_height) max_height = height;
			}

			int num = 0;	//紀錄被chop掉的節點數量
			if(parent[q] == p){		//如果p在下面
				parent[q] = -1;		//q被砍
				height_arr[q] = 0;	//將被砍掉節點的高度設成0
				num++;
				for(int i=0; i<N; i++){
					int cut = i;
					while(parent[cut] != 0 && parent[cut] != -1){
						cut = parent[cut];
						if(cut == q){			//找該節點是否在q之上，若是的話該節點會被chop掉，num++
							parent[i] = -1;
							height_arr[i] = 0;	//將被砍掉節點的高度設成0
							num++;
							break;
						}
					}
				}
			}else{					//q在下面
				parent[p] = -1;		//p被砍
				height_arr[p] = 0;	//將被砍掉節點的高度設成0
				num++;
				for(int i=0; i<N; i++){
					int cut = i;
					while(parent[cut] != 0 && parent[cut] != -1){
						cut = parent[cut];
						if(cut == p){
							parent[i] = -1;
							height_arr[i] = 0;	//將被砍掉節點的高度設成0
							num++;
							break;
						}
					}
				}
			}

			tmp = 0;
			for(int i=0; i<N; i++){		//算出chop後的最高高度
				if(tmp < height_arr[i])
					tmp = height_arr[i];
			}

			if(tmp != max_height) 	//查看最高的高度前後有沒有差異，若有則表示最高的被砍了，因此倒塌
				collapsed = true;
			if(!collapsed)
				return num;
			else
				return -1;
		}
	}

	public static void main(String[] args) {
		ChopTrees ct = new ChopTrees(25);
		ct.union(1, 2);
		ct.union(3, 4);
		ct.union(1, 3);
		ct.union(7, 2);
		ct.union(7, 3);
		ct.union(1, 6);
		ct.union(10, 11);
		ct.union(15, 12);
		ct.union(2, 17);
		ct.union(3, 15);
		ct.union(4, 11);
		ct.union(1, 3);
		ct.union(6, 8);
		ct.union(8, 19);
		ct.union(11, 17);
		ct.union(12, 15);
		ct.union(11, 18);
		ct.union(5, 14);

		System.out.println("After Chop 8, 6 => " + ct.chop(8, 6)); // expected output: After Chop 8, 6 => 1
		System.out.println("After Chop 11, 18 => " + ct.chop(11, 18));// expected output: After Chop 11, 18 => 3
		System.out.println("After Chop 1, 3 => " + ct.chop(1, 3));//  expected output: After Chop 1, 3 => 1

		System.out.println("Union of 20, 9 => " + ct.union(20,9)); // expected output: Union of 20, 9 => true

		System.out.println("After Chop 15, 3 => " + ct.chop(15, 3));//  expected output: After Chop 15, 3 => -1
		System.out.println("After Chop 14, 9 => " + ct.chop(14, 9));//  expected output: After Chop 14, 9 => -1

		System.out.println("Union of 20, 9 => " + ct.union(20,9)); // expected output: Union of 20, 9 => false
	}
}