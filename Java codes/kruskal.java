/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
//import for Scanner and other utility classes
import java.util.*;
import javafx.util.Pair;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
class Edge implements Comparable<Edge>{
    Pair<Integer, Integer> vertices;
    int weight;
    
    public Edge(int st, int en, int w){
        this.vertices = new Pair<>(st,en);
        this.weight = w;
    }
    
    public int compareTo(Edge another){
        return this.weight - another.weight;
    }
} 

class TestClass {
    
    public static int root(int x, int[] parent){
        while(x != parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }    
        return x;
    }
    
    public static void union(int rootX, int rootY, int[] parent, int[] size){
        
        if(size[rootX]<size[rootY]){
            parent[rootX] = parent[rootY];
            size[rootY] += size[rootX];
        }
        else{
            parent[rootY] = parent[rootX];
            size[rootX] += size[rootY];
        }
    }
    
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
        */
        //Scanner
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();                 // Reading input from STDIN
        int m = s.nextInt();
        int a,b,w;
        
        int[] parent = new int[n+1];
        int[] size = new int[n+1];
        
        for(int i=1;i<=n;i++){
            parent[i] = i;
            size[i] = 1;
        }
        
        List<Edge> nodes = new ArrayList<>();
        while(m>0){
            a = s.nextInt();
            b = s.nextInt();
            w = s.nextInt();
            Edge e = new Edge(a,b,w);
            nodes.add(e);
            m--;
        }

        Collections.sort(nodes);
        
        int sum = 0;
        for(Iterator iter = nodes.listIterator(); iter.hasNext(); ){
             Edge ed = (Edge)iter.next();
             int rootX = root(ed.vertices.getKey(),parent);
             int rootY = root(ed.vertices.getValue(),parent);
             
             if(rootX != rootY){
                 sum += ed.weight;
                 union(rootX,rootY,parent,size);
             }
        }
        System.out.println(sum);
        
        
        // Write your code here

    }
}