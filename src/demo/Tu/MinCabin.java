package demo.Tu;

import org.junit.Test;

import java.util.*;
//n个城市 每两个城市之间可能存在一条具有一定路径的道路，求某一个城市为起点，使其到其他城市距离之和为最小
public class MinCabin {
    public MinCabin(){}
    class Edge{
        private int v;
        private int w;
        private int weight;
        public Edge(int v,int w,int weight){
            this.v=v;
            this.w=w;
            this.weight=weight;
        }
        public int either(){return this.v;}
        public int other(int v){if(v==this.v)return w;return v;}
    }

    class Graph{
        private  LinkedList<Edge>[] adj;
        private int V;
        public Graph(int V){
            this.V=V;
            adj=new LinkedList[V];
            for(int v=0;v<V;v++)
                adj[v]=new LinkedList<>();
        }
        public LinkedList<Edge> adj(int v){return adj[v];}
        public void addEdges(Edge edge){
            int v=edge.either();
            int w=edge.other(v);
            this.adj[v].add(edge);
            this.adj[w].add(edge);
        }
    }

    private Map<Integer,Integer> map;
    private TreeMap<Integer, Set<Integer>> treeMap;

    public boolean isEmpty(){return map.isEmpty();}
    public boolean containsKey(int key){return map.containsKey(key);}
    public void insert(int key,int value){
        map.put(key,value);
        if(treeMap.containsKey(value))
            treeMap.get(value).add(key);
        else{
            Set<Integer> set=new HashSet<>();
            set.add(key);
            treeMap.put(value,set);
        }
    }

    public void changeItem(int key,int value){
        Integer oldValue = map.get(key);
        map.put(key,value);
        Set<Integer> set = treeMap.get(oldValue);
        set.remove(key);
        if(set.size()==0)
            treeMap.remove(oldValue);
        if(treeMap.containsKey(value))
            treeMap.get(value).add(key);
        else{
            Set<Integer> s=new HashSet<>();
            s.add(key);
            treeMap.put(value,s);
        }
    }

    public int delMin(){
        Set<Integer> set = treeMap.get(treeMap.firstKey());
        Integer key = set.iterator().next();
        map.remove(key);
        set.remove(key);
        if(set.size()==0)
            treeMap.remove(treeMap.firstKey());
        return key;
    }

    private Graph G;
    private int[] distTo;

    private int min(int start){
        for(int i=0;i<distTo.length;i++)
            distTo[i]=Integer.MAX_VALUE;
        distTo[start]=0;
        map.clear();
        treeMap.clear();
        insert(start,0);
        while(!isEmpty())
            relax(delMin());
        int ans=0;
        for(int i:distTo)
            ans+=i;
        System.out.println("start:"+start+" sum:"+ans);
        return ans;
    }

    private void relax(int v){
        for(Edge edge:G.adj(v)){
            int w = edge.other(v);
            if(distTo[w]>distTo[v]+edge.weight){
                distTo[w]=distTo[v]+edge.weight;
                if(map.containsKey(w))
                    changeItem(w,distTo[w]);
                else
                    insert(w,distTo[w]);
            }
        }
    }

    public int minCabin(int[][] paths,int n){
        map=new HashMap<>();
        treeMap=new TreeMap<>();
        distTo=new int[n];
        G=new Graph(n);
        for(int i=0;i<paths.length;i++)
            G.addEdges(new Edge(paths[i][0],paths[i][1],paths[i][2]));
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int m=min(i);
            if(m<ans)
                ans=m;
        }
        return ans;
    }

    @Test
    public void test(){
        List<Integer> list_1=new ArrayList<>();
        List<Integer> list_2=new ArrayList<>();
        Random random=new Random();
        int[][] paths=new int[300][3];
        for(int i=0;i<100;i++){
            list_1.add(i);
            list_2.add(i);
        }
        for(int i=0;i<3;i++){
            Collections.shuffle(list_1);
            Collections.shuffle(list_2);
            for(int j=0;j<100;j++){
                paths[100*i+j][0]=list_1.get(j);
                paths[100*i+j][1]=list_2.get(j);
                paths[100*i+j][2]=random.nextInt(30)+1;
            }
        }
        System.out.println(Arrays.deepToString(paths));
        int ans = minCabin(paths, 100);
        System.out.println(ans);
    }

}
