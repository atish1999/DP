package Aditya_Verma.concept.MCM;

/*
                                "à¤œà¤¯ à¤¶à¥�à¤°à¥€ à¤•à¥ƒà¤·à¥�à¤£à¤¾"
*/
import java.util.*;
import java.io.*;

public class Count_All_palindromic_Subsequences implements Runnable {

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				this.br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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

	static class FastWriter {
		PrintWriter pw;

		public FastWriter() {
			this.pw = (new PrintWriter(new BufferedOutputStream(System.out)));
		}

		public FastWriter(String s) {
			try {
				this.pw = new PrintWriter(new File(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		public void print(Object object) {
			pw.append("" + object);
		}

		public void println(Object object) {
			print(object);
			pw.append("\n");
		}

		public void println() {
			pw.append("\n");
		}

		public void close() {
			pw.close();
		}

		public void flush() {
			pw.flush();
		}

	}

	static int mod = (int) (1e9 + 7), N = (int) (1e5), INF = Integer.MAX_VALUE, NINF = Integer.MIN_VALUE;
	static FastReader fr = new FastReader();
	static FastWriter out = new FastWriter();

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Count_All_palindromic_Subsequences(), "Main", 1 << 26).start();
	}

	int dp[][];

	void memeset(int dp[][], int val) {
		for (var x : dp)
			Arrays.fill(x, -1);
	}

	int cnt(String s, int i, int j) {
		if (i > j)
			return 0;

		if (i == j) // single character is considered as a palindrome
			return dp[i][j] = 1;

		if (dp[i][j] != -1)
			return dp[i][j];

		if (s.charAt(i) == s.charAt(j))
			return dp[i][j] = 1 + cnt(s, i + 1, j) + cnt(s, i, j - 1);

		return dp[i][j] = cnt(s, i + 1, j) + cnt(s, i, j - 1) - cnt(s, i - 1, j - 1);
	}

	void method_1() { // memozization + recursion
		String s = fr.next();

		int n = s.length();
		dp = new int[n][n];
		memeset(dp, -1);

		out.print(cnt(s, 0, n - 1));
	}

	long countPS(String str) { // top down (tabulation)
		int n = str.length();
		long mod = (long) (1e9 + 7);
		long dp[][] = new long[n][n];

		for (int gap = 0; gap < n; ++gap) {
			for (int i = 0, j = gap; j < n; ++j, ++i) {
				if (gap == 0) {
					dp[i][j] = 1;
				} else if (gap == 1) {
					/*
					 * (1) ab= 'a', 'b' ---> 2 str.charAt(i) != str.charAt(j) (2) aa= 'a', 'a',
					 * 'aa'---->3 str.charAt(i) == str.charAt(j)
					 * 
					 */
					dp[i][j] = str.charAt(i) != str.charAt(j) ? 2 : 3;
				} else {
					if (str.charAt(i) == str.charAt(j)) {
						dp[i][j] = 1 + dp[i + 1][j] + dp[i][j - 1];
					} else {
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
					}

					dp[i][j] = dp[i][j] < 0 ? dp[i][j] + mod : dp[i][j] % mod;
				}
			}
		}

		return dp[0][n - 1];

	}

	void method_2() {
		String s = fr.next();
		out.print(countPS(s));
	}

	void solve() {
		method_1();
		// or
		method_2();
	}

	@Override
	public void run() {
		long start = System.nanoTime(); // Program Start
		boolean testcase = false;
		int t = testcase ? fr.nextInt() : 1;
		while (t-- > 0) {
			solve();
			out.print("\n");
			out.flush();
		}
		out.close();
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

		static List<Integer> sieve(int size) {
			ArrayList<Integer> pr = new ArrayList<Integer>();
			boolean prime[] = new boolean[size];
			for (int i = 2; i < prime.length; i++)
				prime[i] = true;
			for (int i = 2; i * i < prime.length; i++) {
				if (prime[i]) {
					for (int j = i * i; j < prime.length; j += i) {
						prime[j] = false;
					}
				}
			}
			for (int i = 2; i < prime.length; i++)
				if (prime[i])
					pr.add(i);
			return pr;
		}

		static long binary_Expo(long a, long b) {// calculating a^b
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

		static int i_gcd(int a, int b) { // iterative way to calculate gcd.

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

		static int upper_Bound(int a[], int x) { // closest to the left+1

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

		static int lower_Bound(int a[], int x) { // closest to the right

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

		static boolean isSquarefactor(int x, int factor, int target) {
			int s = (int) Math.round(Math.sqrt(x));
			return factor * s * s == target;
		}

		static boolean isSquare(int x) {
			int s = (int) Math.round(Math.sqrt(x));
			return x * x == s;
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

		static int[] z_function(String s) {
			int n = s.length(), z[] = new int[n];
			for (int i = 1, l = 0, r = 0; i < n; ++i) {
				if (i <= r)
					z[i] = Math.min(z[i - l], r - i + 1);

				while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
					++z[i];

				if (i + z[i] - 1 > r) {
					l = i;
					r = i + z[i] - 1;
				}
			}
			return z;
		}

		static int[] kmp(String s) {
			int n = s.length(), pi[] = new int[n];
			for (int i = 1; i < n; ++i) {
				int j = pi[i - 1];

				while (j > 0 && s.charAt(i) != s.charAt(j))
					j = pi[j - 1];

				if (s.charAt(i) == s.charAt(j))
					++j;

				pi[i] = j;

			}

			return pi;
		}
	}

}