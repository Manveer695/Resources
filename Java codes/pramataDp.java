/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static int[][][][] dp = new int[50][50][50][50];
	public static int[][][][] sum = new int[50][50][50][50];
	
	public static int minCost(int[][] b, int rowSt, int rowEn, int colSt, int colEn){
		if(dp[rowSt][rowEn][colSt][colEn] != 0)
			return dp[rowSt][rowEn][colSt][colEn];
		if( (rowSt == rowEn && colSt+1 == colEn) || (colSt == colEn && rowSt+1 == rowEn) ){
			return dp[rowSt][rowEn][colSt][colEn] = b[rowSt][colSt] + b[rowEn][colEn];
		}	
		int minRow=Integer.MAX_VALUE, minCol=Integer.MAX_VALUE;
		int rowCost;
		int colCost;
		int sumCurr = 0;
		
		if(sum[rowSt][rowEn][colSt][colEn] != 0)
			sumCurr = sum[rowSt][rowEn][colSt][colEn];
		else{
			for(int i=rowSt;i<=rowEn;i++){
				for(int j=colSt;j<=colEn;j++){
					sumCurr += b[i][j];
				}
			}
		}
		sum[rowSt][rowEn][colSt][colEn] = sumCurr;
		
		for(int i=rowSt;i<rowEn;i++){
			rowCost = minCost(b,rowSt,i,colSt,colEn)+minCost(b,i+1,rowEn,colSt,colEn)+sumCurr;
			if(rowCost < minRow)
				minRow = rowCost;
		}
		for(int i=colSt;i<colEn;i++){
			colCost = minCost(b,rowSt,rowEn,colSt,i)+minCost(b,rowSt,rowEn,i+1,colEn)+sumCurr;
			if(colCost < minCol)
				minCol = colCost;
		}
		return dp[rowSt][rowEn][colSt][colEn] = Math.min(minRow,minCol);
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m,n;
		String str = br.readLine();
		String[] nums = str.split(" ");
		n = Integer.parseInt(nums[0]);
		m = Integer.parseInt(nums[1]);
		int[][] a = new int[n][m];
		for(int i=0;i<n;i++){
			String[] nums2 = br.readLine().split(" ");
			for(int j=0;j<m;j++){
				a[i][j] = Integer.parseInt(nums2[j]);	
			}
		}
		
		System.out.println(minCost(a,0,n-1,0,m-1));
	}
}