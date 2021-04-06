package demo.Tu.shortpath;

import java.util.LinkedList;
import java.util.List;

public class EdgeWeightDigraph {
    private final int V;    //顶点总数
    private int E;          //边的总数
    private LinkedList<DirectedEdge>[] adj; //邻接表

    public EdgeWeightDigraph(int V){
        this.V=V;
        this.E=0;
        adj=new LinkedList[V];
        for(int v=0;v<V;v++)
            adj[v]=new LinkedList<>();
    }

    public int V(){return this.V;}

    public int E(){return this.E;}

    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        List<DirectedEdge> list=new LinkedList<>();
        for(int v=0;v<V;v++)
            list.addAll(adj[v]);
        return list;
    }
}
