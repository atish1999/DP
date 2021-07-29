package Aditya_Verma.concept.MCM;

/*
								"जय श्री कृष्णा"
*/
import java.util.*;
import java.io.*;

public class Egg_Dropping_Problem_Recursive implements Runnable {

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
	static final FastReader fr = new FastReader(
			"/home/atish/eclipse-workspace/Dynamic_Programming_DP/src/Aditya_Verma/concept/MCM/input.txt");

	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Egg_Dropping_Problem_Recursive(), "Main", 1 << 26).start();
	}

	int dp[][];
	
	int fun(int e, int f) {
/*
CASE:1
	if egg is 1 then min no of attempts would be f because in that 
	case we have to move from the bottom floor. and in worst case
	we have to move upto f th floor from bottom.
CASE:2
	if we have no floor i.e. in that case we don't need to drop our 
	egg our answer would be 0.
CASE:3
	if we have 1 floor in that case 1 would be the answer.because 
	in that case that floor would be the critical floor from which 
	we will be dropping our egg.

 */
		if (e == 1 || f <= 1)
			return f;
		
		if(dp[e][f]!=-1) 
			return dp[e][f];
		
		int min=Integer.MAX_VALUE;
		for(int k=1; k<=f; k++) {
/*
	we are trying to iterate through 1st floor to n-th floor and
	from in each floor we will be trying to calculate all temporary
	answer.
 */
			int temp=1+Math.max(fun(e-1,k-1), fun(e, f-k));
/*
	we are adding 1 beacuse from that floor we are dropping our egg
	means that we are taking 1 attempt.we are considerinng the worst
	case scenario that's why we are taking maximum value of 
	2 scenarios i.e. if we can go upward or downward.
	
	if egg is getting broken it means we have to move downward.
	and if it is not breaking we have to move upward.
	
	as we are considering all the cases thats why we will be 
	considering both the upward and downward cases. worst case 
	scenario means like upto how much extreme extent we can go ??
	i.e. what will be the maximum scenarios of all these two
	
	and as we have to return the minimum answer thats why we are
	taking minimum of all the temporary answers.
 */	
			min=Math.min(min, temp);
		}
		
		return dp[e][f]=min;
	}

	void solve() {
		int e = fr.nextInt(),f=fr.nextInt();
		
		dp=new int[e+1][f+1];
		
		for(int x[]: dp) Arrays.fill(x, -1);
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
