package demo.Tu.MyPrim;

import demo.IndexMinQueue.MyIndexMinPriorityQueue;
import demo.Tu.Edge;
import demo.Tu.EdgeWeightGraph;

import java.util.LinkedList;
import java.util.Queue;

public class MyPrim {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private MyIndexMinPriorityQueue myQueue;
    public MyPrim(EdgeWeightGraph G){
        edgeTo=new Edge[G.V()];
        distTo=new double[G.V()];
        marked=new boolean[G.V()];
        myQueue=new MyIndexMinPriorityQueue();
        for(int v=0;v<G.V();v++)
            distTo[v]=Double.POSITIVE_INFINITY;
        distTo[0]=0.0;
        myQueue.insert(0,0.0);
        while(!myQueue.isEmpty())
            visit(G,myQueue.delMin());
    }

    private void visit(EdgeWeightGraph G,int v){
        marked[v]=true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if(marked[w]) continue;
            if(distTo[w]>e.weight()){
                edgeTo[w]=e;
                distTo[w]=e.weight();
                if(myQueue.contains(w))
                    myQueue.changeItem(w,distTo[w]);
                else
                    myQueue.insert(w,distTo[w]);
            }
        }
    }

    public Queue<Edge> edges(){
        Queue<Edge> queue=new LinkedList<>();
        for (int v=0;v<edgeTo.length;v++)
            queue.offer(edgeTo[v]);
        return queue;
    }

    public double weight(){
        double weight=0.0;
        for(int v=0;v<edgeTo.length;v++)
            if(edgeTo[v]!=null)
                weight+=edgeTo[v].weight();
        return weight;
    }
}
