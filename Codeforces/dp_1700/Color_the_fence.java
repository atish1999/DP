package Codeforces.dp_1700;

/*
							"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Color_the_fence implements Runnable {

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
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

	static int mod = (int) (1e9 + 7);
	static FastReader fr = new FastReader();
	static PrintWriter out = new PrintWriter(System.out, true);

	// static FastReader fr = new
	// FastReader("D://DP/src/Codeforces/dp_1700/dp1700.txt");
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Color_the_fence(), "Main", 1 << 26).start();
	}

	int a[], dp[];
	private int INF = Integer.MAX_VALUE;

	int f(int v) {
		if (v == 0)
			return 0;
		if (v < 0)
			return -1;
		if (dp[v] != -1)
			return dp[v];
		int max = -1;
		for (int i = 0; i < a.length; ++i) {
			int temp = 1 + f(v - a[i]);
			max = Math.max(max, temp);
		}
		return dp[v] = max;
	}

	void solve() {
		int v = fr.nextInt();
		a = fr.nextIntArray(9);
		int min = INF;
		for (int i = 0; i < 9; ++i)
			min = Math.min(min, a[i]);

		if (v == 0 || v < min) {
			out.print(-1);
			return;
		}
		dp = new int[a.length];
		Arrays.fill(dp, -1);
		int ans = f(v), cnt = 0, left = v;

		if (ans == 0) {
			out.print(-1);
			return;
		}

		StringBuilder sb = new StringBuilder();
		while (cnt < ans) {
			for (int i = 8; i >= 0; --i) {
				if (left >= a[i] && f(left - a[i]) + 1 + cnt == ans) {
					sb.append(i + 1);
					left -= a[i];
					++cnt;
					break;
				}
			}
		}
		out.print(sb.toString().trim());
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
