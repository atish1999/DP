package Aditya_Verma.concept.Longest_Common_Subsequence;
/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Printing_SCS implements Runnable {

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
	static final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
	static final FastReader fr = new FastReader();

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Printing_SCS(), "Main", 1 << 26).start();
	}
	static class Pair implements Comparable<Pair> {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.x, o.x);
		}

	}
/*
Given two strings X and Y, print the shortest string that has 
both X and Y as subsequences. 
If multiple shortest supersequence exists, print any one of them.

Examples:

Input: X = "AGGTAB",  Y = "GXTXAYB"
Output: "AGXGTXAYB" OR "AGGXTXAYB" 
OR Any string that represents shortest
supersequence of X and Y

Input: X = "HELLO",  Y = "GEEK"
Output: "GEHEKLLO" OR "GHEEKLLO" OR "HGELLOEK"
OR Any string that represents shortest 
supersequence of X and Y

 */
	static int dp[][];
	static StringBuilder sb;
	void pd() {
		out.print("\nprinting dp matrix:\n");
		for(int x[]: dp) {
			for(int y: x) {
				out.print(y+" ");
			}
			out.print("\n");
		}
	}
	void solve() {
/*
		Time complexity of above solution is O(n^2).
		Auxiliary space used by the program is O(n^2).
*/
		char[] a = fr.next().toCharArray(), b = fr.next().toCharArray();
		int n = a.length, m = b.length;
		dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++)
			dp[i][0] = 0;
		Arrays.fill(dp[0], 0);
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a[i - 1] == b[j - 1]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
/* 
because in worst case we have to add two strings as super sequence
in that case length would be (m+n)
 but for string AGGTAB and GXTXAYB scs would be AGGXTXAYB
 and length would be 9 i.e. we have written lcs one time and other 
 characters have maintained their own frequences and own appearance orders 
 ut if we add (m+n) it means we are adding LCS 2 times that's why to have LCS
 one time we are removing LCS 1 time.
 
*/	
//		List<Pair> al=new ArrayList<>();
		sb=new StringBuilder();
		int i=n,j=m;
		while(i>0 && j>0) {
			if(a[i-1]==b[j-1]) {
//				al.add(new Pair(i,j));
				sb.append(a[i-1]);
				--i;
				--j;
			}else {
				if(dp[i-1][j]>dp[i][j-1]) {
//					al.add(new Pair(i,j));
					sb.append(a[i-1]);
					--i;
				}else {
//					al.add(new Pair(i,j));
					sb.append(b[j-1]);
					--j;
				}
			}
		}
// because if either i or j=0 here means lcs would be 0 
// but in case of scs we have to add characters upto some i if j==0
//or upto some j if i==0 but if both i and j are 0 then we don't have to
// add anything
		while(i>0) {
			sb.append(a[i-1]);
			--i;
		}
		while(j>0) {
			sb.append(b[j-1]);
			--j;
		}
		out.print(sb.reverse().toString().trim());
//		pd();
//		for(Pair p: al) {
//			out.print(p.x+" "+p.y+" "+dp[p.x][p.y]+"\n");
//		}
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
				return a; //because each time b will divide a.
			return gcd(b, a % b);
		}

		static long ceil_div(long a, long b) { // a numerator b denominator
			return (a + b - 1) / b;
		}

		static int getIthBitFromInt(int bits, int i) {
			return (bits >> (i - 1)) & 1;
		}

		static int upper_Bound(int a[], int x) {//closest to the left+1
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

		static int lower_Bound(int a[], int x) {//closest to the right
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

