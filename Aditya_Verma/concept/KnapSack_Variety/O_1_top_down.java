package Aditya_Verma.concept.KnapSack_Variety;

import java.util.*;
import java.io.*;

public class O_1_top_down implements Runnable {

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
	/*
	
Problem Statement:(0|1 Knapsack)

	there is a knapsack with initial capacity W kg and we have individual items in 
	our list each item has its own weight and own value we have to pick the items
	in such a way to the bag so that we can get the maximum profit.and we have to 
	move optimally.
	
	 */

	static int mod = (int) (1e5 + 1);
	static int dp[][];
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new O_1_top_down(), "Main", 1 << 26).start();
	}
//  Recursion + Matrix
	
//	parent of dp--->recursion
//	so first try to write the recursive code-->then memoize the code
//	after that move to the topdown_approach

//	recursive_code-->memoize--->top_down_Approach
	
/*
	wt[]=weight of individual items will be stored in this array
	val[]=value of individual items will be stored in this array
	W=capacity of the knapsack
	n=size of the above arrays(i.e. wt and val) or number of items in the store
 */
/*
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

//		if the value is already stored we don't have to do further recursive calls
//		we can stop there
		if(dp[n][W]!=-1) return dp[n][W];
		if(wt[n-1]<=W) {
			return dp[n][W]=Math.max(val[n-1]+knapsack(wt,val,W-wt[n-1],n-1), //if we choose
					        knapsack(wt,val,W,n-1));//if we don't choose
		}else { // as wt of the item is larger than the knapsack's capacity 
//			we will not be able to choose thats why we are searching in next (n-1)
//			items
			return dp[n][W]= knapsack(wt,val,W,n-1);
		}
		
	}
*/
//	in case of top_down approach we have to fill oth row and oth column of
//	dp matrix
	static void init(int dp[][],int n) {
			Arrays.fill(dp[0], 0);
			for(int i=1; i<=n; i++) dp[i][0]=0;
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
//	in recursion function value of n and were changing that's why we are taking
//	dimensions in n and w and we have to store the value upto n and w
//	that's why [n + 1][W + 1]
			dp=new int[n + 1][W + 1];
//	initialize this dp array with -1
			init(dp,n);
//	we will be filling remaining (n-1)*(W-1) with the help of first filled
//	0th row and 0th column
			for(int i=1; i<=n; i++) {
				
				for(int j=1; j<=W; j++) {
					if(wt[i-1]<=W) {
						dp[i][j]=Math.max(val[i-1]+dp[i-1][j-wt[i-1]],dp[i-1][j]);
					}else {
						dp[i][j]=dp[i-1][j];
					}
				}
			}
			
//	answer will be present in dp[n][W]
			System.out.println(dp[n][W]); //it is the max profit
		}
		long end = System.nanoTime(); // Program End
		System.err.println("Time taken: " + (end - start) / 1000000 + " ms");
	}
}
