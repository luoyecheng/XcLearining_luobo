package demo.Tu.BellmanFord;

import demo.Tu.shortpath.DirectedEdge;
import demo.Tu.shortpath.EdgeWeightDigraph;

import java.util.LinkedList;
import java.util.Queue;

public class BellmanFord {
    private double[] distTo;                        //从起点到某个顶点的路径长度
    private DirectedEdge[] edgeTo;                  //从起点到某个顶点的最后一条边
    private boolean[] onQ;                          //判断顶点是否在队列中
    private Queue<Integer> queue;                   //正在被放松的顶点
    private int cost;                               //relax()调用的次数
    private Iterable<DirectedEdge> cycle;           //  edgeTo[]中是否存在环


    public BellmanFord(EdgeWeightDigraph G,int s){
        distTo=new double[G.V()];
        edgeTo=new DirectedEdge[G.V()];
        onQ=new boolean[G.V()];
        queue=new LinkedList<>();
        for(int v=0;v<G.V();v++)
            distTo[v]=Double.POSITIVE_INFINITY;
        distTo[0]=0.0;
        queue.offer(s);
        onQ[s]=true;
    }

    private void relax(EdgeWeightDigraph G,int v){
        for(DirectedEdge e:G.adj(v)){
            int w=e.to();
            if(distTo[w]>distTo[v]+e.getWeight()){
                distTo[w]=distTo[v]+e.getWeight();
                edgeTo[w]=e;
                if(!onQ[w]){
                    queue.offer(w);
                    onQ[w]=true;
                }
            }
            //if(cost++%G.V()==0)

        }
    }

    private void findNegativeCycle(){
        int V=edgeTo.length;
        EdgeWeightDigraph spt=new EdgeWeightDigraph(V);
        for(int v=0;v<V;v++)
            if(edgeTo[v]!=null)
                spt.addEdge(edgeTo[v]);

    }
}
