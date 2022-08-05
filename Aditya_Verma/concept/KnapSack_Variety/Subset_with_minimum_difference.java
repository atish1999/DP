package Aditya_Verma.concept.KnapSack_Variety;
import java.util.*;
import java.io.*;
public class Subset_with_minimum_difference{
/*
Problem statement: parent_problem -> (O|1 KnapSack)
   there will be an array of numbers we have to divide the array into 2
   subsets so that difference between them would be minimum.and print that
   minimum difference
   i.e.
       int arr[]={1,5,11,6}; (ans)-> 1
       if we take s1={1,5,6} & s2={11} // null sets are also allowed
       here sum(s1)=12 & sum(s2)=11.
       ans->sum(s1)-sum(s2)=(12-11)=1.
*/
   static boolean dp[][];
   static int n,sum;
   static void init(){
       for(int i=1; i<=sum; i++) dp[0][i]= false;
       for(int i=0; i<=n; i++) dp[i][0]=true;
   }
   static void print_dp(){
       for(boolean x[]: dp){
           for(boolean e: x) System.out.print(e==true?"T ":"F ");
           System.out.println();
       }
   }
   static void solve(int arr[]){
       init();//initialize dp here
       for(int i=1; i<=n; i++){
           for(int j=1; j<=sum; j++){
               if(arr[i-1]<=j){// we can choose it or we can't
                   dp[i][j]=dp[i-1][j] || dp[i-1][j-arr[i-1]];
               }else{
                   dp[i][j]=dp[i-1][j];// we are not choosing the  ith element of the array
               }
           }
       }
   }
   public static void main(String[] args){
       Scanner sc=new Scanner(System.in);
       boolean testcase=true;
       int t=testcase?sc.nextInt():1;
       while(t-->0){
           n=sc.nextInt();
           int a[]=new int[n];
           for(int i=0; i<n; i++){
               a[i]=sc.nextInt();
               sum+=a[i];
           }
           dp=new boolean[n + 1][sum + 1];
           solve(a);
           print_dp();
   /*
       s1+s2=sum
       s2=sum-s1
       we have to calculate minimum of (s2-s1)
       i.e. min(s2-s1)=min(sum-s1-s1)
                      =min(sum-2*s1)
       i.e. if we can get the sum of one part, another part sum can also be found by brute force.
       so we should have to check upto (sum/2)
       and our answer=min(min, sum-dp[n][i]); i=>[0,(sum/2)]
 
 
    */
           long res=0;
           for(int i=sum/2; i>=0; i++){
               if(dp[n][i]) {
                   res=i;
                   break;
               }
           }
           System.out.println(sum-2*res);
       }
   }
}


