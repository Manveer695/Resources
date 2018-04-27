/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public static void buildSegmentTree(int[] segTree, int in, int[] a, int st, int end){
		if(st == end){
			segTree[in] = a[st];
			return;
		}
		int mid = (st+end)/2;
		buildSegmentTree(segTree, 2*in, a, st, mid);
		buildSegmentTree(segTree, 2*in+1, a, mid+1, end);
		segTree[in] = (segTree[2*in] > segTree[2*in+1] ? segTree[2*in] : segTree[2*in+1]);
	}
	
	public static int maxInRange(int[] segTree, int in, int st, int end, int l, int r){
		if(st>end || st>r || end<l){
			return 0;
		}
		if(st>=l && end<=r){
			return segTree[in];	
		}
		int mid = (st+end)/2;
		int left = maxInRange(segTree,2*in,st,mid,l,r);
		int right = maxInRange(segTree,2*in+1,mid+1,end,l,r);
		return ((left > right) ? left : right); 
	}
	
	public static void updateLazyRange(int[] segTree, int[] lazy, int in, int st, int end, int l, int r, int val){
		if(lazy[in] != 0){
			segTree[in] += lazy[in];
			if(st != end){
				lazy[2*in] += lazy[in];
				lazy[2*in+1] += lazy[in];
			}
			lazy[in] = 0;
		}
		if(st>end || st>r || end<l){
			return;
		}
		if(st>=l && end<=r){
			segTree[in] += val; 
			if(st != end){
				lazy[2*in] += val;
				lazy[2*in+1] += val;
			}
			return;
		}
		int mid = (st+end)/2;
		updateLazyRange(segTree,lazy,2*in,st,mid,l,r,val);
		updateLazyRange(segTree,lazy,2*in+1,mid+1,end,l,r,val);
		segTree[in] = ((segTree[2*in+1]>segTree[2*in])?segTree[2*in+1]:segTree[2*in]);
	}
	
	public static int maxLazyRange(int[] segTree, int[] lazy, int in, int st, int end, int l, int r){
		if(lazy[in] != 0){
			segTree[in] += lazy[in];
			if(st != end){
				lazy[2*in] += lazy[in];
				lazy[2*in+1] += lazy[in];
			}
			lazy[in] = 0;
		}
		if(st>end || st>r || end<l){
			return 0;
		}
		if(st>=l && end<=r){
			return segTree[in];
		}
		int mid = (st+end)/2;
		int left = maxLazyRange(segTree,lazy,2*in,st,mid,l,r);
		int right = maxLazyRange(segTree,lazy,2*in+1,mid+1,end,l,r);
		return ((left>right)?left:right);
	}
	/*public static void permuteArr(int[] segTree, int[] permute, int n){
		for(int i=0;i<n;i++){
			updateLazyRange(segTree,lazy,2*in+1,mid+1,end,l,r,val);
		}	
	}*/
	
	public static void main (String[] args) throws java.lang.Exception
	{
		int[] a = {1,2,3,4,5,6,7,8,9};
		int[] segTree = new int[2*a.length];
		buildSegmentTree(segTree, 1, a, 0, 8);
		for(int i=1;i<segTree.length;i++){
			System.out.println(segTree[i]);
		}
		System.out.println("kid "+maxInRange(segTree,1,0,8,3,6));
		int[] lazy = new int[2*a.length];
		System.out.println("kid2 "+maxLazyRange(segTree,lazy,1,0,8,3,6));
		updateLazyRange(segTree,lazy,1,0,8,3,6,8);
		System.out.println("kid2 "+maxLazyRange(segTree,lazy,1,0,8,3,6));
		int[] permute = new int[2*a.length];
		//permuteArr(segTree,permute,9);
	}
}