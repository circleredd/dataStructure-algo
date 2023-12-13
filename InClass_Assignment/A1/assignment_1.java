//package ITR20230213; 



//todo: student 1 id & name, student 2 id & name
//todo: write code for nodesWithDepthOfTreeHeight method, see details in class slides
//todo (optional): write code for componentSize method, see details in class slides
//DO NOT EDIT other functions NOR add global variables.

public class UnionFind2023 {

	// QuickUnionUF from
	// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/QuickUnionUF.java.html
	// JavaDoc
	// https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/QuickUnionUF.html
	public static class QuickUnionUF {
		private int[] parent; // parent[i] = parent of i
		private int count; // number of components

		/**
		 * Initializes an empty union-find data structure with {@code n} elements
		 * {@code 0} through {@code n-1}. Initially, each element is in its own set.
		 *
		 * @param n the number of elements
		 * @throws IllegalArgumentException if {@code n < 0}
		 */
		public QuickUnionUF(int n) {
			parent = new int[n];
			count = n;
			for (int i = 0; i < n; i++) {
				parent[i] = i;
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
		 * @param p an element
		 * @return the canonical element of the set containing {@code p}
		 * @throws IllegalArgumentException unless {@code 0 <= p < n}
		 */
		public int find(int p) {
			validate(p);
			while (p != parent[p])
				p = parent[p];
			return p;
		}

		// validate that p is a valid index
		// 不用在意，只是給find()裡面用
		private void validate(int p) {
			int n = parent.length;
			if (p < 0 || p >= n) {
				throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
			}
		}

		/**
		 * Returns true if the two elements are in the same set.
		 *
		 * @param p one element
		 * @param q the other element
		 * @return {@code true} if {@code p} and {@code q} are in the same set;
		 *         {@code false} otherwise
		 * @throws IllegalArgumentException unless both {@code 0 <= p < n} and
		 *                                  {@code 0 <= q < n}
		 * @deprecated Replace with two calls to {@link #find(int)}.
		 */
		@Deprecated
		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		/**
		 * Merges the set containing element {@code p} with the set containing element
		 * {@code q}.
		 *
		 * @param p one element
		 * @param q the other element
		 * @throws IllegalArgumentException unless both {@code 0 <= p < n} and
		 *                                  {@code 0 <= q < n}
		 */
		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ)
				return;
			parent[rootP] = rootQ;
			count--;
		}

		// todo: complete the method below to return a list of nodes in the same tree as
		// node p,
		// that have the depth of the tree's height.
		public int[] nodesWithDepthOfTreeHeight(int p) {
			
			int max = -1; //將max設為-1，以利後面在arr[]中找出最大值(也就是最深的深度)
			int[] arr = new int[parent.length];

			int countMax = 0; //用來計算arr[]中，與Max相等的值有多少個，以利後面宣告一個arrMax[]來存放要輸出的值(答案)

			
			//計算每個node的深度
			for (int i = 0; i < parent.length; i++) {
				int j = i;
				int depth = 0;
				if (find(p) != find(j)) { 
					//如果該node與p(題目欲找的node)沒有connected，則將該node的depth設為-1，並continue;
					depth = -1;
					continue;
				}
				while (j != parent[j]) { 
					//如果該node與p(題目欲找的node)有connected，則計算該node的depth
					j = parent[j];
					depth++;
				}
				arr[i] = depth; //將各node的depth值存入arr[]這個陣列
			}

			// traverse arr[]這個陣列，以利找出Max值(也就是最深的深度)
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > max) {
					max = arr[i];
				}

			}

			//計算arr[]這個陣列中，有幾個Max值(也就是最深的深度)
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == max) {
					countMax++;
				}
			}
			//宣告一個名為arrMax的陣列，其大小為countMax(arr[]這個陣列中的Max的個數)
			int[] arrMax = new int[countMax];
			int j = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == max) {
					arrMax[j++] = i;
				}
			}
			//回傳 arrMax[]
			return arrMax;

			// 把該高度同層的所有值都print出來

		}

		public static void printNodes(int[] nodes, int node) {
			int i;
			String output = "";
			for (i = 0; i < nodes.length; i++) {
				if (nodes[i] != -1) {
					output += nodes[i] + ",";
				}
			}
			if (output != "") {
				output = output.substring(0, output.length() - 1);
			}
			System.out.println("Nodes with depth of tree height in " + node + "'s tree : " + output);
		}
	}

	public static void main(String[] args) {
		QuickUnionUF uf = new QuickUnionUF(10);
		uf.union(1, 2);
		uf.union(3, 2);
		uf.union(5, 4);
		uf.union(4, 0);
		uf.union(2, 0);
		uf.union(7, 6);
		uf.union(8, 6);
		uf.union(9, 6);

		uf.printNodes(uf.nodesWithDepthOfTreeHeight(0), 0); // expected output 1,3,5
		uf.printNodes(uf.nodesWithDepthOfTreeHeight(2), 2); // expected output 1,3,5
		uf.printNodes(uf.nodesWithDepthOfTreeHeight(7), 7); // expected output 7,8,9

		System.out.println();

	}
}
