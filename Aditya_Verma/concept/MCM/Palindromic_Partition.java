package Concepts;

/*
							"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Palindromic_Partition implements Runnable {

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

	static int mod = (int) (1e9 + 7);
//	static final FastReader fr = new FastReader();
	static final FastReader fr = new FastReader("D://DP/src/Concepts/input.txt");
	static final PrintWriter out = new PrintWriter(System.out, true);

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Palindromic_Partition(), "Main", 1 << 26).start();
	}

	int dp[][];

	boolean isPalindrome(char[] s, int i, int j) {

		for (int l = i, r = j; l < r; ++l, --r) {
			if (s[l] != s[r])
				return false;
		}

		return true;
	}

	int pn(char s[], int i, int j) {
		if (i >= j)
			return 0;
		if (isPalindrome(s, i, j))
			return 0;
		if (dp[i][j] != -1)
			return dp[i][j];

		int min = Integer.MAX_VALUE;
		for (int k = i; k <= j - 1; k++) {
			int left = dp[i][k] != -1 ? dp[i][k] : pn(s, i, k);
			int right = dp[k + 1][j] != -1 ? dp[k + 1][j] : pn(s, k + 1, j);
			int temp = left + right + 1;
			min = Math.min(min, temp);
		}

		return dp[i][j] = min;
	}

	void solve() {
		char pal[] = fr.next().toCharArray();
		int n = pal.length;
		dp = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], -1);
		out.print(pn(pal, 0, n - 1));

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
