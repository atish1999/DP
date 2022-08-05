package Aditya_Verma.concept.MCM;

/*
							"à¤œà¤¯ à¤¶à¥�à¤°à¥€ à¤•à¥ƒà¤·à¥�à¤£à¤¾"
*/
import java.util.*;

import java.io.*;

public class Scrambled_String_Re implements Runnable {

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
		new Thread(null, new Scrambled_String_Re(), "Main", 1 << 26).start();
	}

	/*
	 * 
	 * 
	 * Scramble String using Memoization(Dynamic Programming) Given a string s1, we
	 * may represent it as a binary tree by partitioning it to two non-empty
	 * substrings recursively. Below is one possible representation of A =
	 * â€œgreatâ€�: great / \ gr eat / \ / \ g r e at / \ a t To scramble the
	 * string, we may choose any non-leaf node and swap its two children.
	 * 
	 * For example, if we choose the node â€œgrâ€� and swap its two children, it
	 * produces a scrambled string â€œrgeatâ€�.
	 * 
	 * rgeat / \ rg eat / \ / \ r g e at / \ a t We say that â€œrgeatâ€� is a
	 * scrambled string of â€œgreatâ€�.
	 * 
	 * Similarly, if we continue to swap the children of nodes â€œeatâ€� and
	 * â€œatâ€�, it produces a scrambled string â€œrgtaeâ€�.
	 * 
	 * rgtae / \ rg tae / \ / \ r g ta e / \ t a We say that â€œrgtaeâ€� is a
	 * scrambled string of â€œgreatâ€�.
	 */
	static Map<String, Boolean> h;

	boolean fun(String a, String b) {
		if (a.equals(b))
			return true;
		if (a.length() <= 1)
			return false;

		String key = a + "#" + b;
		if (h.containsKey(key)) {
			return h.get(key);
		}
		int n = a.length();

		for (int i = 1; i < n; i++) {
			if ((fun(a.substring(0, i), b.substring(n - i)) && fun(a.substring(i), b.substring(0, n - i)))
					|| ((fun(a.substring(0, i), b.substring(0, i))) && (fun(a.substring(i), b.substring(i))))) {
				h.put(key, true);
				return true;
			}

		}
		h.put(key, false);
		return false;
	}

	void solve() {
		String a = fr.next(), b = fr.next();
//		if both strings are of different length then answer can't be possible
		if (a.length() != b.length()) {
			out.print("no");
			return;
		}
//		if both the strings are empty then they are scrambled to each other
		if (a.length() == 0 && b.length() == 0) {
			out.print("yes");
			return;
		}

		h = new HashMap<>();
		out.print(fun(a, b) ? "yes" : "no");
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

	/*
	 * 
	 * 6 great great great rgate great grewq abcde abcdes great rgeat great graet
	 * 
	 * yes yes no no yes yes
	 * 
	 * 
	 */
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
