package Aditya_Verma.concept.Unbounded_KnapSack;

/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Rod_cutting implements Runnable {

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

	static int mod = (int) (1e9 + 7);
	static final PrintWriter out = new PrintWriter(System.out);
	static final FastReader fr = new FastReader();

	/*
	 * Rod Cutting Problem Given a rod of length n inches and an array of prices
	 * that contains prices of all pieces of size smaller than n. Determine the
	 * maximum value obtainable by cutting up the rod and selling the pieces.
	 * Example: if length of the rod is 8 and the values of different pieces are
	 * given as following, t hen the maximum obtainable value is 22 (by cutting in
	 * two pieces of lengths 2 and 6)
	 * 
	 * 
	 * length | 1 2 3 4 5 6 7 8 -------------------------------------------- price |
	 * 1 5 8 9 10 17 17 20
	 * 
	 */
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Rod_cutting(), "Main", 1 << 26).start();
	}

	/*
	 * int rod_cutting(int L, int N, int len[], int price[]) { 
	 * 	if(L==0) return 0;
	 * 	if(len[N-1]<=L) 
	 * return Math.max(price[N-1]+rod_cutting(L-len[N-1], N, len,price), 
	 * rod_cutting(L, N-1, len, price)); 
	   return
	     rod_cutting(L,N-1,len,price); 
	 }
	 */
	void solve() {
		int l = fr.nextInt();
		int p[] = fr.nextIntArray(l);
//		int len[]=new int[l];
//		for(int i=1; i<=l; i++) {
//			len[i-1]=i;
//		}
		int dp[][] = new int[l + 1][l + 1];
		dp[0][0] = 0;
		for (int i = 1; i <= l; i++) {
			dp[i][0] = 0;// when length will be 0 then max profit would be 0
			dp[0][i] = 0;// when there is no piece available then max profit would be 0
		}
		for (int i = 1; i < l + 1; i++) {
			for (int j = 1; j < l + 1; j++) {
				if (i <= j) {// i denotes len[i-1] if len[i-1]<=j then we have choices
//	whether we can pick that length or not
					dp[i][j] = Math.max(p[i - 1] + dp[i][j - i], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		out.print(dp[l][l]);
	}

	@Override
	public void run() {
		long start = System.nanoTime(); // Program Start
		boolean testcase = true;
		int t = testcase ? fr.nextInt() : 1;
		while (t-- > 0) {
			solve();
			out.print("\n");
			out.flush();
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
				return a; // because each time b will divide a.
			return gcd(b, a % b);
		}

		static long ceil_div(long a, long b) { // a numerator b denominator
			return (a + b - 1) / b;
		}

		static int getIthBitFromInt(int bits, int i) {
			return (bits >> (i - 1)) & 1;
		}

		static int upper_Bound(int a[], int x) {// closest to the left+1
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

		static int lower_Bound(int a[], int x) {// closest to the right
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
