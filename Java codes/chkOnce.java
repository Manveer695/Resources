/* IMPORTANT: Multiple classes and nested static classes are supported */
 
/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
//import for Scanner and other utility classes
import java.util.*;
 
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
 
class TestClass {

	public static int[] dfs(int node, int[][] a, List<List<Integer>> tree){
		if(a[node-1][26] == 1){
			return a[node-1];
		}

		List<Integer> neighs = tree.get(node-1);
        if(neighs.size() == 1 && node != 1){
            if(a[neighs.get(0)-1][26] == 1){
                return a[node-1];
            }
        }

        int[] b = new int[26];
		for(Integer i: neighs){
			b = dfs(i,a,tree);
            a[i-1][26] = 1;
            for(int k=0;k<26;k++){
                a[node-1][k] += b[k];
            }
		}
        a[node-1][26] = 1;

        return a[node-1];
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
        int q = s.nextInt();
        int[][] a = new int[n][27];
        List<List<Integer>> tree = new ArrayList<List<Integer>>();
 
        for(int i=0;i<n;i++)
        {
        	a[i][26] = -1;
            tree.add(new ArrayList<Integer>());
            a[i][s.next().charAt(0) - 'a'] = 1;
        }   
 
        int u,v;
        for(int i=1;i<n;i++){
            u = s.nextInt();
            v = s.nextInt();
            tree.get(u-1).add(v);
            tree.get(v-1).add(u);
        }
 
        String st;
        for(int i=0;i<q;i++){
            u = s.nextInt();
            st = s.nextLine();
 
            dfs(u,a,tree);
            a[u-1][26] = 1;
 
            int sum = 0;
            int size = st.length();
            for(int j=0; j<size;j++){
            	if(a[u-1][st[j]-'a'] == 0){
            		sum+=1;
            	}
            	else{
            		a[u-1][st[j]-'a'] -= 1;
            	}
            }
            System.out.println(sum);
        }
    }
}
 