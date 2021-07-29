package Aditya_Verma.concept.KnapSack_Variety;
import java.util.*;
import java.io.*;

public class Equal_sum_partition implements Runnable {

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
/*
 	the question is equal sum partititon i.e. can we divide the array into 2 subsets
 	i.e. p1 and p2 such that sum(p1)==sum(p2). if we can do so print "YES"
 	otherwise print "NO"
 */
	static int mod = (int) (1e9 + 7);
	static boolean dp[][];
	public static void main(String[] args) throws java.lang.Exception {
		new Thread(null, new Equal_sum_partition(), "Main", 1 << 26).start();
	}
	static void init(boolean dp[][], int n) {
//  If there is no element in the array we can't get sum>=0 sum can be only 0
//	because there will exist a null set { }
		Arrays.fill(dp[0], false); 
//	if there is any elemnts in the array still we can get sum==0
//  because a null subset will be always present there
		for(int i=0; i<n+1; i++) dp[i][0]=true;
	}
	static boolean solve_dp(boolean dp[][], int arr[], int n, int sum) {
		
		init(dp,n);
		for(int i=1; i<=n; i++) {
			
			for(int j=1; j<=sum; j++) {
				if(arr[i-1]<=j) { // here we can choose the element or we also can ignore
					dp[i][j]=dp[i-1][j-arr[i-1]] || dp[i-1][j];
				}else {// we are not choosing elements because value is greater tham sum
					dp[i][j]=dp[i-1][j];
				}
			}
		}
		
		return dp[n][sum];
	}
	@Override
	public void run() {
		long start = System.nanoTime(); // Program Start
		FastReader fr = new FastReader();
		int t = fr.nextInt();
		while (t-- > 0) {
			int n = fr.nextInt();
			int a[] = new int[n],sum=0;
			for(int i=0; i<n; i++) {
				a[i]=fr.nextInt();
				sum+=a[i];
			}
/*
    let, sum(p1)=s1 and sum(p2)=s2
    if(s1==s2) it means sum of array = s1+s2=2*s1=even number
    i.e. if sum of the array is even only then there is a chance that we can divide
    the array into two equal sum partition
*/

			if((sum&1)==1){//if the sum is odd then we can't divide the array
				System.out.println("NO");//into two parts each having equal sum
				continue;
			}
//	otherwise there will be a chance i.e. we have to check whether it can be possible
//	s1-s2=0 or not 
//  as s1 -s2==0 i.e.-> s1==s2
//	sum=2*s1-> {s1==(sum)/2}
//	i.e.  if we are able to find sum of one subset other will be alreday there
//	i.e. if there is present a subset with sum == (sum)/2 then we can have the answer	
			dp=new boolean[n + 1][sum/2 + 1];
			if(solve_dp(dp,a,n,sum/2)) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
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

