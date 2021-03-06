package demo.Tu;

import java.util.*;

public class Graph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;
    public Graph(int V){
        this.V=V;
        this.E=0;
        adj=new LinkedList[V];
        for(int i=0;i<V;i++)
            adj[i]=new LinkedList<>();
    }
    public int V(){ return this.V;}
    public int E(){ return this.E;}
    public void addEdge(int v,int w){
        if(v>=this.V||w>=this.V)
            return;
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

}
