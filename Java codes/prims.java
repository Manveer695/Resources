/* Prims */
/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Edge{
    Integer secondVertex;
    int weight;
    
    public Edge(int st, int w){
        this.secondVertex = st;
        this.weight = w;
    }
} 

class Ideone
{
	public static Comparator<Edge> weightCompare = new Comparator<Edge>(){
		public int compare(Edge c1, Edge c2) {
		            return (int) (c1.weight - c2.weight);
		        }	
	};
	
	public static int prims(int vertex, List<List<Edge>> nodes, int[] marked){
		Queue<Edge> minVertice = new PriorityQueue<>(nodes.size(),weightCompare);
		Edge new1 = new Edge(vertex,0);
		minVertice.add(new1);
		int minimum = 0;
		while(minVertice.peek() != null){
			// Iterator it = minVertice.iterator();
			// while(it.hasNext())
			// 	System.out.print(((Edge)it.next()).weight+" ");
			// System.out.println();
			
			Edge top = minVertice.poll();
			int node = top.secondVertex;
			if(marked[node] == 1)
				continue;
			marked[node] = 1;
			minimum += top.weight;
			for(Iterator iter = nodes.get(node-1).listIterator(); iter.hasNext(); ){
				Edge ed = (Edge)iter.next();
				int node2 = ed.secondVertex;
				if(marked[node2] != 1){
					minVertice.add(ed);
				}
			}
		}
		return minimum;
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner s = new Scanner(System.in);
	        int n = s.nextInt();                 // Reading input from STDIN
	        int m = s.nextInt();
	        int a,b,w;
	        
	        List<List<Edge>> nodes = new ArrayList<List<Edge>>();
	        
	        for(int i=0;i<n;i++){
	        	nodes.add(new ArrayList<Edge>());	
	        }
	        
	        while(m>0){
	            a = s.nextInt();
	            b = s.nextInt();
	            w = s.nextInt();
	            Edge e1 = new Edge(b,w);
	            Edge e2 = new Edge(a,w);
	            nodes.get(a-1).add(e1);
	            nodes.get(b-1).add(e2);
	            m--;
	        }
	        
	        int[] marked = new int[n+1];
	        System.out.println(prims(1,nodes,marked));
	}
}