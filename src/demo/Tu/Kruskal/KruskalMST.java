package demo.Tu.Kruskal;

import demo.Tu.Edge;
import demo.Tu.EdgeWeightGraph;
import demo.UnionFind;

import java.util.*;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weightSum;
    public KruskalMST(EdgeWeightGraph G){
        mst=new LinkedList<>();
        List<Edge> list=G.edges();
        Collections.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return (int) (o1.weight()-o2.weight());
            }
        });
        UnionFind uf=new UnionFind(G.V());
        int index=0;
        int m=list.size();
        while(index<m&&mst.size()<G.V()-1){
            Edge e=list.get(index++);
            int v=e.either(),w=e.other(v);
            if(uf.connected(v,w)) continue;
            uf.union_quickUnion(v,w);
            mst.offer(e);
            weightSum+=e.weight();
        }
    }

    public Iterable<Edge> edges(){return mst;}

    public double getWeightSum(){return weightSum;}
}
