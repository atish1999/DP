package Aditya_Verma.concept.MCM;

/*
							"à¤œà¤¯ à¤¶à¥�à¤°à¥€ à¤•à¥ƒà¤·à¥�à¤£à¤¾"
*/
import java.util.*;
import java.io.*;

public class MCM implements Runnable {

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (Exception e) {
				out.print(e + "\n");
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

	static int mod = (int) (1e9 + 7), INF = Integer.MAX_VALUE;
	static final FastReader fr = new FastReader("D://DP/src/Atcoder/input.txt");
	static final PrintWriter out = new PrintWriter(System.out, true);

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new MCM(), "Main", 1 << 26).start();
	}

	int t[][];

	int mcm(int a[], int i, int j) {

		if (i >= j)
			return 0;
// for overriding sub problems
		if (t[i][j] != -1)
			return t[i][j];
		int min = INF;

		for (int k = i; k <= j - 1; k++) {
			int temp = mcm(a, i, k) + mcm(a, k + 1, j) + (a[i - 1] * a[k] * a[j]);
			min = Math.min(min, temp);
		}
		return t[i][j] = min;
	}

	void top_down(int a[], int n) {
		int dp[][] = new int[n][n];
//		writing base cases
		for (int i = 1; i < n; i++)
			dp[i][i] = 0;

		for (int l = 2; l < n; l++) {

			for (int i = 1; i <= n - l; i++) {
				int j = i + l - 1;

				dp[i][j] = INF;
				for (int k = i; k <= j - 1; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (a[i - 1] * a[k] * a[j]));
				}
			}
		}

		out.print(dp[1][n - 1]);
	}

	void solve() {
		int n = fr.nextInt();
		int a[] = fr.nextIntArray(n);
		t = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(t[i], -1);
//		out.print(mcm(a, 1, n - 1));
		top_down(a, n);
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

		static void l_sort(int a[]) {
			ArrayList<Integer> al = new ArrayList<>();
			for (int x : a)
				al.add(x);
			Collections.sort(al);
			for (int i = 0; i < a.length; i++)
				a[i] = al.get(i);
		}

		static void heap_sort(int a[]) { // heap sort
			PriorityQueue<Integer> q = new PriorityQueue<>();
			for (int i = 0; i < a.length; i++)
				q.add(a[i]);
			for (int i = 0; i < a.length; i++)
				a[i] = q.poll();
		}

		static void ruffle_sort(int[] a) {
			// random_shuffle
			Random r = new Random();
			int n = a.length;
			for (int i = 0; i < n; i++) {
				int oi = r.nextInt(n);
				int temp = a[i];
				a[i] = a[oi];
				a[oi] = temp;
			}

			// sort
			Arrays.sort(a);
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
