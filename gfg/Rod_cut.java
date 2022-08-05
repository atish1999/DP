package gfg;

/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Rod_cut implements Runnable {

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
	static int dp[][];

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Rod_cut(), "Main", 1 << 26).start();
	}

	int a[], v, NINF = Integer.MIN_VALUE;

	int f(int l, int curr_V) {

		if (curr_V == 0)
			return 0;

		if (l == 0)
			return NINF;

		if (dp[l][curr_V] != -1)
			return dp[l][curr_V];

		if (l == 1)
			return dp[l][curr_V] = v % a[l - 1] == 0 ? v / a[l - 1] : NINF;

		int ans = f(l - 1, curr_V);

		if (a[l - 1] <= curr_V) {

			ans = Math.max(ans, 1 + f(l, curr_V - a[l - 1]));
		}

		return dp[l][curr_V] = ans;

	}

//	int cut(int l, int n, int len[]) {
//		if(n>0) out.print(l+" "+n+"->len["+(n-1)+"]="+len[n-1]+"\n");
//		if(n==0) out.print(l+" "+n+"->"+len[n]+"\n");
//		if(l==0) return dp[n][l]=0;
//		if(n==0) return dp[n][l]=-1;
//		if(dp[n][l]!=-1) return dp[n][l];
//		if(len[n-1]<=l) {
//			int a=(1+cut(l-len[n-1],n,len));
//			out.print("\nvalue of a: "+a+" when l is "+l+" and n is "+n+"\n");
//			int b=cut(l,n-1,len),max=Math.max(a, b);
//			out.print("\n("+a+" "+b+" max =>"+max+")<->("+l+" "+n+")\n");
//			return dp[n][l]=max;
//		}
//		return dp[n][l]=cut(l,n-1,len);
//	}
	void solve() {
		int l = fr.nextInt(), n = fr.nextInt();
		int len[] = fr.nextIntArray(n);
		dp = new int[4][l + 1];
		for (int i = 1; i < 4; i++)
			dp[i][0] = 0;
		Arrays.fill(dp[0], Integer.MIN_VALUE);
		for (int i = 1; i < l + 1; i++)
			dp[1][i] = (i % len[0] == 0) ? (i / len[0]) : Integer.MIN_VALUE;
		for (int i = 2; i < 4; i++) {
			for (int j = 1; j < l + 1; j++) {
				if (len[i - 1] <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], 1 + dp[i][j - len[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		out.print(dp[3][l] <= 0 ? 0 : dp[3][l]);
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
