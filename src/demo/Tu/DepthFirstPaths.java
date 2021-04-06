package demo.Tu;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    public DepthFirstPaths(Graph G, int s){
        marked=new boolean[G.V()];
        edgeTo=new int[G.V()];
        this.s=s;
        dfs(G,s);
    }
    private void dfs(Graph G,int v){
        marked[v]=true;
        for(int w:G.adj(v)){
            if(!marked[w]){
                edgeTo[w]=v;
                dfs(G,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))   return null;
        Deque<Integer> deque=new LinkedList<>();
        for(int x=v;x!=s;x=edgeTo[x])
            deque.offerLast(x);
        deque.offerLast(s);
        return deque;
    }

    public static void main(String[] args) {
        Graph graph=new Graph(20);
        for(int i=0;i<15;i++)
            graph.addEdge(i,3);
        DepthFirstPaths depthFirstPaths=new DepthFirstPaths(graph,3);
        Iterable<Integer> iterable = depthFirstPaths.pathTo(6);
        for (Integer integer : iterable) System.out.println(integer);

    }
}
