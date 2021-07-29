package Codeforces.dp_1700;

/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Password_DP implements Runnable {

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
	// static final FastReader fr = new FastReader();
	static final FastWriter out = new FastWriter();
	static final FastReader fr = new FastReader(
			"/home/atish/eclipse-workspace/Dynamic_Programming_DP/src/Aditya_Verma/concept/MCM/input.txt");

	// static final FastWriter out = new FastWriter(
	// "/home/atish/eclipse-workspace/Dynamic_Programming_DP/src/Aditya_Verma/concept/MCM/output.txt");
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Password_DP(), "Main", 1 << 26).start();
	}

	int[] z_function(String s) {
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

	void solve() {
		String s = fr.next();
		int z[] = z_function(s);

		int max = NINF, res = -1;

		for (int i = 1, n = s.length(); i < n; ++i) {
			if (z[i] == (n - i) && max >= (n - i)) {
				res = (n - i);
				break;
			}
			max = Math.max(max, z[i]);
		}

		out.print(res == -1 ? "Just a legend" : s.substring(0, res));
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
	}

}
