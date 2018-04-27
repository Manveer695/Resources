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

class TestClass {
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
        int u,v,w;
        
        List<List<Pair<Integer, Integer>>> edges = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            edges.add(new ArrayList<Pair<Integer,Integer>>());     
        }
        
        for(int i=0;i<m;i++){
            u = s.nextInt();
            v = s.nextInt();
            w = s.nextInt();
            edges.get(u-1).add(new Pair<Integer, Integer>(v,w));
        }
        
        int[] visited = new int[n];
        int[] dist = new int[n];
        Arrays.fill(dist, 1000000000);
        PriorityQueue<Pair<Integer,Integer>> min = new PriorityQueue<Pair<Integer,Integer>>(n,new Comparator<Pair<Integer,Integer>>(){
            public int compare(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2){
                if(p1.getValue() < p2.getValue()) return -1;
                if(p1.getValue() > p2.getValue()) return 1;
                return 0;
            }    
        });
        
        min.add(new Pair<Integer, Integer>(1,0));
        dist[0] = 0;
        
        while(min.peek() != null){
            Pair<Integer, Integer> node = min.poll();
            int vtx = node.getKey();
            int wt = node.getValue();
            
            if(visited[vtx-1] == 1)
                continue;
            visited[vtx-1] = 1;
            
            for(Pair<Integer, Integer> p : edges.get(vtx-1)){
                if(dist[p.getKey()-1] > dist[vtx-1] + p.getValue()){
                    dist[p.getKey()-1] = dist[vtx-1] + p.getValue();
                    min.add(new Pair<Integer, Integer>(p.getKey(),dist[p.getKey()-1]));
                }
            }
        }
        
        for(int i=0;i<n;i++){
            System.out.print(dist[i]+" ");
        }
        // Write your code here

    }
}
