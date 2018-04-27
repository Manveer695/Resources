static int ans;
    static List<List<Integer>> nodes;
    static int[] visited;
    static int k;
    static int[] l;
    
    public static int gcd(int a, int b){
        if(a == 0)
            return b;
        return gcd(b%a,a);
    }
    
    public static List<Pair<Integer, Integer>> dfs(int node){
        if(nodes.get(node).size() == 1 && visited[nodes.get(node).get(0)] == 1){
            int temp = 1;
            if(l[node] > 1 && temp > ans && (temp%k == 0))
                ans = temp;
            //System.out.println(node+" "+temp);
            visited[node] = 1;
            
            List<Pair<Integer, Integer>> lp = new ArrayList<>();
            
            lp.add(new Pair(temp, l[node]));
            lp.add(new Pair(temp, l[node]));
            return lp;
        }    
        
        List<Integer> children = nodes.get(node);
        visited[node] = 1;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int max1Gcd = Integer.MIN_VALUE, max2Gcd = Integer.MIN_VALUE;
        int temp, gcdVal1, gcdVal2, newGcd;
        List<Pair<Integer, Integer>> lp = new ArrayList<>();
        
        for(Integer i: children){
            if(visited[i] == 1)
                continue;
                
            lp = dfs(i);
            gcdVal1 = lp.get(0).getValue();
            temp = lp.get(0).getKey();
            if(gcdVal1 == 1){
                gcdVal2 = lp.get(1).getValue();
                if(gcdVal2 == 1)
                    continue;
                else{
                    gcdVal1 = gcdVal2;
                    temp = lp.get(1).getKey();
                }
            }
                
            
            if(temp%k == 0)
                newGcd = l[node];
            else
                newGcd = gcd(l[node],gcdVal1);
            
            if(newGcd == 1)
            {
                gcdVal2 = lp.get(1).getValue();
                if(gcdVal2 == 1)
                    continue;
                else{
                    gcdVal1 = gcdVal2;
                    temp = lp.get(1).getKey();
                    if(temp%k == 0)
                        newGcd = l[node];
                    else
                        newGcd = gcd(l[node],gcdVal1);
                    
                    if(newGcd == 1)
                        continue;
                }
            }
                
            if(temp > max1)
            {
                max2 = max1; 
                max2Gcd = max1Gcd;
                max1 = temp;
                max1Gcd = newGcd;
            }    
            else if(temp > max2){
                max2 = temp;
                max2Gcd = newGcd;
            }
        }
        
        int finalGcd;
        if(max1Gcd > Integer.MIN_VALUE)
        {
            if(max2Gcd > Integer.MIN_VALUE){
                newGcd = gcd(max1Gcd, max2Gcd);
                if(finalGcd != 1){
                    temp = 1+((max1 == Integer.MIN_VALUE)?0:max1)+((max2 == Integer.MIN_VALUE)?0:max2);
                    finalGcd = newGcd;
                }
                else{
                    temp = 1+((max1 == Integer.MIN_VALUE)?0:max1);
                    finalGcd = max1Gcd;
                }
            }
            else{
                temp = 1+((max1 == Integer.MIN_VALUE)?0:max1);
                finalGcd = max1Gcd;
            }
            
            if(temp > ans && (temp % k == 0))
                    ans = temp;
            
            lp.clear();
            lp.add(new Pair(1+((max1 == Integer.MIN_VALUE)?0:max1), finalGcd));
            
            temp = 1;
            if(l[node] > 1 && temp > ans && (temp%k == 0))
                ans = temp;
            lp.add(new Pair(temp, l[node]));
            
            return lp;        
            // //System.out.println(node+" 1 "+temp);
            // return 1 + ((max1 == Integer.MIN_VALUE)?0:max1);
        }    
        else{
            temp = 1;
            if(l[node] > 1 && temp > ans && (temp%k == 0))
                ans = temp;
                
            lp.clear();
            lp.add(new Pair(temp, l[node]));
            lp.add(new Pair(temp, l[node]));
            
            return lp;
        }
    }
    
    public static void main(String[] args) throws Exception {
        new Thread(null, new TestClass(), "Main", 1<<26).start();
    }
    
    public void run(){
        // solve the problem here
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();                 // Reading input from STDIN
        k = s.nextInt();
        
        nodes = new ArrayList<>();
        for(int i=0;i<=n;i++)
            nodes.add(new ArrayList<>());
        
        for(int i=0;i<n-1;i++){
            int u = s.nextInt();
            int v = s.nextInt();
            nodes.get(u).add(v);
            nodes.get(v).add(u);
        }
        
        l = new int[n+1];
        for(int i=1;i<=n;i++){
            l[i] = s.nextInt();
        }
        
        visited = new int[n+1];
        ans = Integer.MIN_VALUE;
        dfs(1);
        
        System.out.println(ans-1 < 0 ? 0 : ans-1);
        
    }