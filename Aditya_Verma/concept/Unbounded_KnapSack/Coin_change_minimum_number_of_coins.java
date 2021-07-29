package Aditya_Verma.concept.Unbounded_KnapSack;

/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Coin_change_minimum_number_of_coins implements Runnable {

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

	static int mod = (int) (1e9 + 7), INF = Integer.MAX_VALUE;
	static final PrintWriter out = new PrintWriter(System.out);
	static final FastReader fr = new FastReader();

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Coin_change_minimum_number_of_coins(), "Main", 1 << 26).start();
	}

	/*
	 * Coin Change Problem Minimum Numbers of coins Given a value V, if we want to
	 * make change for V cents, and we have infinite supply of each of C = { C1, C2,
	 * .. , Cm} valued coins, what is the minimum number of coins to make the
	 * change? Example:
	 * 
	 * Input: coins[] = {25, 10, 5}, V = 30 Output: Minimum 2 coins required We can
	 * use one coin of 25 cents and one of 5 cents
	 */
	static int dp[][];

	void solve() {
		int n = fr.nextInt(), target = fr.nextInt();
		int coin[] = fr.nextIntArray(n);
		dp = new int[n + 1][target + 1];
//	when there will be no coins in the array then to get the desired target value we
//	need INFINITY no. of coins.
		for (int i = 0; i < target + 1; i++)
			dp[0][i] = INF - 1;
// when there will be coins in the array then to get the desired sum = 0, minimum no. of
// coins needed will be 0 (i.e. choosing null set i.e. not choosing any coins from the array)
		for (int i = 1; i < n + 1; i++)
			dp[i][0] = 0;
// if there is 1 coin in the array then minimum no. of coin required to get the desired result
// would be the target value(T) divided by that single coin value(V)
// If (T%V==0) it means we can get the desired target by adding (T/V) coin value.
// else we will not be able to get the desired target from that V means we need infinite 
// number of coins to get that value
		for (int i = 1; i < target + 1; i++) {
			if (i % coin[0] == 0)
				dp[1][i] = i / coin[0];
			else
				dp[1][i] = INF - 1;
		}
		/*
		 * Q. In the initializing step why are we using INF-1 instaed of using INF? Ans:
		 * dp[i][j] =Math.min(dp[i - 1][j] , 1+ dp[i][j - coin[i - 1]]); if we are
		 * getting Integer.MAX_VALUE from dp[i][j - coin[i - 1]] then adding 1 will be
		 * giving overflow error i.e. it will be giving some negative number. that's why
		 * to avoid such issue we have used this INF-1 because 1+INF-1=INF
		 */
		for (int i = 2; i < n + 1; i++) {
			for (int j = 1; j < target + 1; j++) {
				if (coin[i - 1] <= j) {
//		If we are choosing the coin then we are adding 1 coin in our list
					dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coin[i - 1]]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		out.print(dp[n][target]);
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
