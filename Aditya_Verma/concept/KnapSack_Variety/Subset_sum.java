package Aditya_Verma.concept.KnapSack_Variety;
import java.util.*;
import java.io.*;

public class Subset_sum implements Runnable {

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
Problem statement:
	numbers will be given and with that a sum will be given, we have to find whether 
	a set can exist whose sum of the elemnts will be equal to that given sum
	if true print yes otherwise print no
	*/

	static int mod = (int) (1e9 + 7);
	static boolean dp[][];
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Subset_sum(), "Main", 1 << 26).start();
	}
/*
 this is the recursive_approach
	static boolean subset_rec(int ar[], int n, int sum) {
		if(sum==0) return true;
		if(n==0) return false;
		
		if(ar[n-1]<=sum) {
			 return (subset_rec(ar,n-1,sum-ar[n-1]) || subset_rec(ar,n-1,sum));
		}else {
			return subset_rec(ar,n-1,sum);
		}
		
	}

*/
	static void init(boolean dp[][], int n, int sum) {
		for(int i=0; i<=n; i++) {
			for(int j=0; j<=sum; j++) {
				if(i==0) dp[i][j]=false;
				if(j==0) dp[i][j]=true;
			}
		}
	}
	static void solve(boolean dp[][], int ar[], int n, int sum) {
		init(dp,n,sum);
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=sum; j++) {
				if(ar[i-1]<=j) {
					dp[i][j]= (dp[i-1][j-ar[i-1]]) || (dp[i-1][j]);
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}
	}
	@Override
	public void run() {
		long start = System.nanoTime(); // Program Start
		FastReader fr = new FastReader();
		int t = fr.nextInt();
		while (t-- > 0) {
			int n = fr.nextInt();
			int a[] = fr.nextIntArray(n);
			int sum=fr.nextInt();
			dp=new boolean[n + 1][sum + 1];
			solve(dp,a,n,sum);
//		our answer will be on the bottom right corner of the matrix
			System.out.println(dp[n][sum]?"YES":"NO");
			
		}
		long end = System.nanoTime(); // Program End
		System.err.println("Time taken: " + (end - start) / 1000000 + " ms");
	}

	static class CP {

		static boolean isPrime(long n) {
			if (n <= 1)
				return false;
			if (n == 2 || n == 3)
				return true;
			if (n % 2 == 0 || n % 3 == 0)
				return false;
			for (int i = 5; i * i <= n; i += 6) {
				if (n % i == 0 || n % (i + 2) == 0)
					return false;
			}
			return true;
		}

		static long binary_Expo(long a, long b) { // calculating a^b
			long res = 1;
			while (b != 0) {
				if ((b & 1) == 1) {
					res *= a;
					--b;
				}
				a *= a;
				b /= 2;
			}
			return res;
		}

		static long Modular_Expo(long a, long b) {
			long res = 1;
			while (b != 0) {
				if ((b & 1) == 1) {
					res = (res * a) % mod;
					--b;
				}
				a = (a * a) % mod;
				b /= 2;
			}
			return res;
		}

		static int i_gcd(int a, int b) {// iterative way to calculate gcd.
			while (true) {
				if (b == 0)
					return a;
				int c = a;
				a = b;
				b = c % b;
			}
		}

		static long gcd(long a, long b) {// here b is the remainder
			if (b == 0)
				return a; //because each time b will divide a.
			return gcd(b, a % b);
		}

		static long ceil_div(long a, long b) { // a numerator b denominator
			return (a + b - 1) / b;
		}

		static int getIthBitFromInt(int bits, int i) {
			return (bits >> (i - 1)) & 1;
		}

		static int upper_Bound(int a[], int x) {//closest to the left+1
			int l = -1, r = a.length;
			while (l + 1 < r) {
				int m = (l + r) >>> 1;
				if (a[m] <= x)
					l = m;
				else
					r = m;
			}
			return l + 1;
		}

		static int lower_Bound(int a[], int x) {//closest to the right
			int l = -1, r = a.length;
			while (l + 1 < r) {
				int m = (l + r) >>> 1;
				if (a[m] >= x)
					r = m;
				else
					l = m;
			}
			return r;
		}

		static void sort(int a[]) {
			PriorityQueue<Integer> q = new PriorityQueue<>();
			for (int i = 0; i < a.length; i++)
				q.add(a[i]);
			for (int i = 0; i < a.length; i++)
				a[i] = q.poll();
		}

		static int getMax(int arr[], int n) {
			int mx = arr[0];
			for (int i = 1; i < n; i++)
				if (arr[i] > mx)
					mx = arr[i];
			return mx;
		}
	}

}

