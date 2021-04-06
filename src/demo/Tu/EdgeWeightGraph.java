package demo.Tu;

import java.util.LinkedList;
import java.util.List;

//加权无向图
public class EdgeWeightGraph {
    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;

    public EdgeWeightGraph(int V){
        this.V=V;
        this.E=0;
        adj=new LinkedList[V];
        for(int v=0;v<V;v++)
            adj[v]=new LinkedList<>();
    }

    public int V(){return this.V;}

    public int E(){return this.E;}

    public void addEdge(Edge e){
        int v=e.either(),w=e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public List<Edge> adj(int v){return adj[v];}

    public List<Edge> edges(){
        List<Edge> list=new LinkedList<>();
        for(int v=0;v<V;v++)
            for(Edge e:adj[v])
                if(e.other(v)>v)
                    list.add(e);
        return list;
    }
}
