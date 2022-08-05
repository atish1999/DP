package Aditya_Verma.concept.KnapSack_Variety;
import java.util.*;
import java.io.*;

public class O_1_recursive_basic implements Runnable {

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
		int[] nextIntArray(int size) {
			int[] arr = new int[size];
			for (int i = 0; i < size; ++i)
				arr[i] = nextInt();
			return arr;
		}

		long[] nextLongArray(int size) {
			long[] arr = new long[size];
			for (int i = 0; i < size; ++i)
				arr[i] = nextLong();
			return arr;
		}

		double[] nextDoubleArray(int size) {
			double[] arr = new double[size];
			for (int i = 0; i < size; ++i)
				arr[i] = nextDouble();
			return arr;
		}
	}

	static int mod = (int) (1e5 + 1);
/*
	
	Problem Statement:(0|1 Knapsack)

    there is a knapsack with initial capacity W kg and we have individual items in 
    our list each item has its own weight and own value we have to pick the items
    in such a way to the bag so that we can get the maximum profit.and we have to 
	move optimally.
		
 */
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new O_1_recursive_basic(), "Main", 1 << 26).start();
	}
//  basic recursive function (only recursion)
/*
	wt[]=weight of individual items will be stored in this array
	val[]=value of individual items will be stored in this array
	W=capacity of the knapsack
	n=size of the above arrays(i.e. wt and val) or number of items in the store
 */
	static int knapsack(int wt[], int val[], int W, int n) {
//		base condn (we are looking for smaller valid inputs)
		if(n==0 || W==0) return 0;//when there are no items or we don't have
//	any knapsacks to store the items in such above cases our max profit will be 0
//		choice diagram
/*
	 here we have three choices of selecting the item
	 if(wt of the item <= knapsack's present capacity ){
	   then we can choose the item or we can leave the item and go for next item
	   if we are choosing the item in that case in our profit value of that item will 
	   be added.else we have to search in next (n-1) items
	 }else{ i.e. wt of the item > knapsack's present capacity)
	 	in tnis case we can not choose the item
	 }
   
 */
		if(wt[n-1]<=W) {
			return Math.max(val[n-1]+knapsack(wt,val,W-wt[n-1],n-1), //if we choose
					        knapsack(wt,val,W,n-1));//if we don't choose
		}else { // as wt of the item is larger than the knapsack's capacity 
//			we will not be able to choose thats why we are searching in next (n-1)
//			items
			return knapsack(wt,val,W,n-1);
		}
		
	}

	@Override
	public void run() {
		long start = System.nanoTime(); // Program Start
		FastReader fr = new FastReader();
		int t = fr.nextInt();
		while (t-- > 0) {
			int n=fr.nextInt(),W=fr.nextInt(),
			wt[]=fr.nextIntArray(n),
			val[]=fr.nextIntArray(n);
			
			int max_Profit=knapsack(wt,val,W,n);
			
			System.out.println(max_Profit);
		}
		long end = System.nanoTime(); // Program End
		System.err.println("Time taken: " + (end - start) / 1000000 + " ms");
	}
}

