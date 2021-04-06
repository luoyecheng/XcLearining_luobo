package demo.Tu.prim;

import demo.IndexMinQueue.IndexMinPriorityQueue;
import demo.Tu.Edge;
import demo.Tu.EdgeWeightGraph;

import java.util.LinkedList;
import java.util.Queue;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPriorityQueue<Double> pq;

    public PrimMST(EdgeWeightGraph G){
        edgeTo=new Edge[G.V()];
        distTo=new double[G.V()];
        marked=new boolean[G.V()];
        for(int v=0;v<G.V();v++)
            distTo[v]=Double.POSITIVE_INFINITY;
        distTo[0]=0.0;
        pq.insert(0,0.0);
        while(!pq.isEmpty())
            visit(G,pq.delMin());
    }

    private void visit(EdgeWeightGraph G,int v){
        marked[v]=true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if(marked[w]) continue;
            if(e.weight()<distTo[w]){
                edgeTo[w]=e;
                distTo[w]=e.weight();
                if(pq.contains(w)) pq.changeItem(w,distTo[w]);
                else
                    pq.insert(w,distTo[w]);
            }
        }
    }

    public Queue<Edge> edges(){
        Queue<Edge> queue=new LinkedList<>();
        for(int i=0;i<edgeTo.length;i++)
            queue.offer(edgeTo[i]);
        return queue;
    }
}
