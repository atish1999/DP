package Aditya_Verma.concept.KnapSack_Variety;

import java.util.*;
import java.io.*;

public class Count_subsets_with_sum_equals_K implements Runnable {

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
					System.err.println(e);
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

	static int mod = (int) (1e9 + 7);

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Count_subsets_with_sum_equals_K(), "Main", 1 << 26).start();
	}

	/*
	 * static int count(int arr[],int n,int sum) { if(sum==0) return 1; if(n==0)
	 * return 0; if(arr[n-1]<=sum) { return
	 * count(arr,n-1,sum-arr[n-1])+count(arr,n-1,sum); }else { return
	 * count(arr,n-1,sum); } }
	 */
//	this will be the case when there will be non zero positive elements in array
//	ande sum should be greater than equal to 0.
//	Array should not contain negative elements
	static int dp[][], x;

	static void init(int sum, int n, int arr[]) {
		dp[0][0] = 1;
		for (int i = 1; i <= sum; i++)
			dp[0][i] = 0;
//		for(int i=0; i<=n; i++) dp[i][0]=1; // we are not initializing this column
//		beacuse for checking 0 incase of sum equals to 0
//		(int) Math.pow(2, number_of_zero(arr,i)); 
	}

//	static int  number_of_zero(int arr[], int length) {
//		int c=0;
//		for(int  i=0; i<length; i++) {
//			if(arr[i]==0) ++c;
//		}
//		return c;
//	}
	static void solve(int sum, int n, int arr[]) {
		init(sum, n, arr);
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {// instaed of starting from 1
//		we are starting from 0 because in case of sum==0 we don't know 
//		how many elements of 0 are present in the array
				if (arr[i - 1] <= j) {
					dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[n][sum]);
	}

	static void print_dp(int dp[][]) {
		for (int x[] : dp) {
			for (int e : x)
				System.out.print(e + " ");
			System.out.println();
		}
	}

	static void print_subsets(int arr[], int n, int sum) {
		int c = 0;
		ArrayList<Integer> al = new ArrayList<>();
//		this technique will be helpful upto n<=20
//		because time complexity is (O(2^n*n) i.e. exponential time complexity
		for (int i = 0; i < (1 << n); i++) {
			int x = 0;
			al.clear();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {// checking if the jth bit is on or not
					x += arr[j];
					al.add(arr[j]);
//					System.out.print(arr[j]+" ");
				}
			}
			if (x == sum) {
				++c;
				System.out.println(al);
			}
//			System.out.println();
		}
		System.out.println(c);
	}

	@Override
	public void run() {
		long start = System.nanoTime(); // Program Start
		FastReader fr = new FastReader();
		int t = fr.nextInt();
		while (t-- > 0) {
			int n = fr.nextInt(), sum = fr.nextInt();
			int a[] = fr.nextIntArray(n);
//			System.out.println(count(a,n,sum));
			dp = new int[n + 1][sum + 1];
//			print_subsets(a,n,sum);
			solve(sum, n, a);
//			print_dp(dp);
		}
		long end = System.nanoTime(); // Program End
//		System.err.println("Time taken: " + (end - start) / 1000000 + " ms");
	}

}
