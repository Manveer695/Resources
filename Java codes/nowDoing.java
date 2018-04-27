/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	public void insertHeap(List<Integer> heap, int ind){
		
		int max=ind;
		int parInd = (max-1)/2;
		if(heap[parInd] > heap[max]){
			max=parInd;
		}
		
		int scndChild = parInd*2+1;
		if(scndChild == ind){
			scndChild += 1;
		}
		if(scndChild < heap.size()){
			if(heap[scndChild] > heap[max])
				max = scndChild;
		}
		
		if(max != ind)
		{
			int temp = heap[ind];
			heap[ind] = heap[max];
			heap[max] = temp; 
			insertHeap(heap, max);
		}
	}
	
	public List<Integer> heapify(List<Integer> arrSums){
		List<Integer> heap = new ArrayList<>();
		heap.add(arrSums[0]);
		for(Integer i:arrSums.subList(1,arrSums.size())){
			heap.add(i);
			insertHeap(heap, heap.size()-1);
		}
		
		return heap;
	}
	
	public List<List<int>> arrSplit(int[] arr, int k){
		List<List<Integer>> res = new ArrayList<>();
		
		for(int i = arr.length-1; i>=arr.length-k; i--){
			List<Integer> temp = new ArrayList<>();
			temp.add(arr[i]);
			res.add(temp);
		}
		
		List<Integer> arrSums = new ArrayList<>();
		for(List<Integer> a:res){
			arrSums.add(a[0]);
		}
		heapify(arrSums);
		
		return res;
	}
	
	public static void main(String args[]){
		int[] arr = new int[10];
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<10;i++)
			a[i] = sc.nextInt();
			
		int k = 3;
		List<List<Integer>> arrSplited = arrSplit(arr, k); 
	}
}