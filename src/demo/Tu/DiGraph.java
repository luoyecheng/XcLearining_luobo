package demo.Tu;

import java.util.LinkedList;

public class DiGraph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public DiGraph(int V){
        this.V=V;
        this.E=0;
        adj=(LinkedList<Integer>[]) new LinkedList[V];
        for(int i=0;i<V;i++)
            adj[i]=new LinkedList<>();
    }

    public int V(){return V;}

    public int E(){return E;}

    public void addEdge(int v,int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){return adj[v];}

    public DiGraph reverse(){
        DiGraph R=new DiGraph(V);
        for(int v=0;v<V;v++)
            for(int w:adj(v))
                R.addEdge(w,v);
        return R;
    }

}
