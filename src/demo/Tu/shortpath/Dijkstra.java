package demo.Tu.shortpath;

import demo.IndexMinQueue.IndexMinPriorityQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Dijkstra {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPriorityQueue<Double> pq;

    public Dijkstra(EdgeWeightDigraph G,int s){
        edgeTo=new DirectedEdge[G.V()];
        distTo=new double[G.V()];
        pq=new IndexMinPriorityQueue<>(G.V());
        for(int v=0;v<G.V();v++)
            distTo[v]= Double.POSITIVE_INFINITY;
        distTo[0]=0.0;
        pq.insert(0,0.0);
        while(!pq.isEmpty())
            relax(G,pq.delMin());
    }

    private void relax(EdgeWeightDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){
            int w=e.to();
            if(distTo[w]>distTo[v]+e.getWeight()){
                distTo[w]=distTo[v]+e.getWeight();
                edgeTo[w]=e;
                if(pq.contains(w))
                    pq.changeItem(w,distTo[w]);
                else
                    pq.insert(w,distTo[w]);
            }
        }
    }

    public double distTo(int v){return distTo[v];}

    public boolean hasPathTo(int v){return distTo[v]<Double.POSITIVE_INFINITY;}

    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Queue<DirectedEdge> queue=new LinkedList<>();
        DirectedEdge e=edgeTo[v];
        while(e!=null){
            queue.offer(e);
            e=edgeTo[e.from()];
        }
        return queue;
    }
}
