/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	static tym = 2;
	void dfs(int root, int child, int curr, List<List<Integer>> edges, List<Integer> arti, int[] parent, int[] visited, int[] lowTym, int[] visitTym){
		List<Integer> nodeEdges = edges.get(curr);
		Iterator<Integer> iter = nodeEdges.listIterator();
		while(iter.hasNext()){
			Integer node = iter.next();
			
			if(node == parent[curr] || visited[node] == true)
				continue;
			
			// increasing the children of root.
			if(curr == root)
				child++;
				
			lowTym[node] = visitTym[node] = tym++;
		}
	} 
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int n, m;
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		
		List<List<Integer>> edges = new ArrayList<>();
		
		for(int i=1;i<=n;i++){
			edges.add(new ArrayList<Integer>());
		}
		
		int a,b;
		for(int i=0;i<m;i++){
			a = s.nextInt();
			b = s.nextInt();
			edges.get(a).add(b);
			edges.get(b).add(a);
		}
		
		int root = 1;
		List<Integer> arti = new ArrayList<>();
		int child=0;
		
		int[] parent = new int[n+1];
		// observed that generally elements in a cycle have the same lowTym
		int[] lowTym = new int[n+1];
		int[] visitTym = new int[n+1];
		boolean[] visited = new boolean[n+1];

		parent[1] = -1;
		visited[1] = true;
		lowTym[1] = 1;
		visitTym[1] = 1;
		
		dfs(root,child,edges,arti,parent,visited,lowTym,visitTym);
		
		Iterator<Integer> iter = arti.listIterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}