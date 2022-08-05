package Aditya_Verma.concept.MCM;

/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Printing_Brackets_in_MCM implements Runnable {

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
				out.print(e + "\n");
				out.flush();
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
	static final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
//	static final FastReader fr = new FastReader();
	static final FastReader fr = new FastReader(
			"/home/atish/eclipse-workspace/Dynamic_Programming_DP/src/Aditya_Verma/concept/MCM/input.txt");

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Printing_Brackets_in_MCM(), "Main", 1 << 26).start();
	}

	/*
	 * Given a sequence of matrices, find the most efficient way to multiply these
	 * matrices together. The problem is not actually to perform the
	 * multiplications, but merely to decide in which order to perform the
	 * multiplications. We have many options to multiply a chain of matrices because
	 * matrix multiplication is associative. In other words, no matter how we
	 * parenthesize the product, the result will be the same. For example, if we had
	 * four matrices A, B, C, and D, we would have:
	 * 
	 * (ABC)D = (AB)(CD) = A(BCD) = ....
	 * 
	 * However, the order in which we parenthesize the product affects the number of
	 * simple arithmetic operations needed to compute the product, or the
	 * efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix,
	 * and C is a 5 × 60 matrix. Then,
	 * 
	 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations A(BC) =
	 * (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
	 * 
	 * Clearly the first parenthesization requires less number of operations. Given
	 * an array p[] which represents the chain of matrices such that the ith matrix
	 * Ai is of dimension p[i-1] x p[i]. We need to write a function
	 * MatrixChainOrder() that should return the minimum number of multiplications
	 * needed to multiply the chain.
	 * 
	 * Input: p[] = {40, 20, 30, 10, 30} Output: Optimal parenthesization is
	 * ((A(BC))D) Optimal cost of parenthesization is 26000 There are 4 matrices of
	 * dimensions 40x20, 20x30, 30x10 and 10x30. Let the input 4 matrices be A, B, C
	 * and D. The minimum number of multiplications are obtained by putting
	 * parenthesis in following way (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30
	 * 
	 * Input: p[] = {10, 20, 30, 40, 30} Output: Optimal parenthesization is
	 * (((AB)C)D) Optimal cost of parenthesization is 30000 There are 4 matrices of
	 * dimensions 10x20, 20x30, 30x40 and 40x30. Let the input 4 matrices be A, B, C
	 * and D. The minimum number of multiplications are obtained by putting
	 * parenthesis in following way ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30
	 * 
	 * Input: p[] = {10, 20, 30} Output: Optimal parenthesization is (AB) Optimal
	 * cost of parenthesization is 6000 There are only two matrices of dimensions
	 * 10x20 and 20x30. So there is only one way to multiply the matrices, cost of
	 * which is 10*20*30
	 * 
	 * 
	 * input: 1 5 40 20 30 10 30
	 * 
	 * output: minimum cost: 26000 ((A(BC))D)
	 * 
	 */
	static int dp[][], bracket[][];
	static char name = 'A';

//	function for printing brackets
	void fun(int i, int j, int n) {

		if (i == j) {
			out.print(name++);
		} else {
			out.print("(");

			fun(i, bracket[i][j], n);

			fun(bracket[i][j] + 1, j, n);

			out.print(")");
		}

	}

	void solve() {
		int n = fr.nextInt();
		int a[] = fr.nextIntArray(n);
		dp = new int[n][n];
		bracket = new int[n][n];

		for (int i = 1; i < n; i++) {
			dp[i][i] = 0;
		}

		for (int L = 2; L < n; L++) { // chain length
			for (int i = 1; i < n - L + 1; i++) {
				int j = i + L - 1;

				if (j == n)
					continue;

				dp[i][j] = INF;

				for (int k = i; k <= (j - 1); k++) {
					int cost = dp[i][k] + dp[k + 1][j] + (a[i - 1] * a[k] * a[j]);

					if (cost < dp[i][j]) {
						dp[i][j] = cost;
						bracket[i][j] = k;
					}
				}
			}
		}

		out.printf("minimum cost: %d\n", dp[1][n - 1]);

		fun(1, n - 1, n);

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
