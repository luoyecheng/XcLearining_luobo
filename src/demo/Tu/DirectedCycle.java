package demo.Tu;

import demo.Tu.DiGraph;

import java.util.Deque;
import java.util.LinkedList;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeT0;
    private Deque<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(DiGraph G){
        marked=new boolean[G.V()];
        edgeT0=new int[G.V()];
        onStack=new boolean[G.V()];

    }

    private void dfs(DiGraph G,int v){
        onStack[v]=true;
        marked[v]=true;
        for(int w:G.adj(v)){
            if(this.hasCycle()) return;
            else if(!marked[w]){
                edgeT0[w]=v;
                dfs(G,w);
            }else if(onStack[w]){
                cycle=new LinkedList<>();
                for(int x=v;x!=w;x=edgeT0[x])
                    cycle.offerLast(x);
                cycle.offerLast(w);
                cycle.offerLast(v);
            }
        }
        onStack[v]=false;
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    public Iterable<Integer> cycle(){return cycle;}
}
