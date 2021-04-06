package demo.Tu.MyPrim;

import java.util.HashSet;
import java.util.Set;

public class Prim {
    public Set<Edge> getMinTree(Point point,int maxPoint){
        if(point == null){
            throw new NullPointerException();
        }
        /**已经找到的点*/
        Set<Point> reachedPoint = new HashSet<Point>();
        reachedPoint.add(point);
        /**已经确认可以作为结果集的边*/
        Set<Edge> resultEdge = new HashSet<Edge>();
        /**等待检索的边*/
        Set<Edge> waitingRetrievalEdge = new HashSet<Edge>();
        waitingRetrievalEdge.addAll(point.getEdges());
        Edge tempMaxEdge = new Edge();
        tempMaxEdge.setWeight(Integer.MAX_VALUE);
        while(true){
            Edge minEdge = tempMaxEdge;
            /**
             * 从待检索的边中找出最小边
             * */
            for(Edge edge:waitingRetrievalEdge){
                if(minEdge.getWeight() >= edge.getWeight()){
                    minEdge = edge;
                }
            }
            /**将找到的边 的另外一个端点  加入已经找到的集合*/
            for(Point p:minEdge.getPoint()){
                if(!reachedPoint.contains(p)){
                    reachedPoint.add(p);
                    /**将这个点中没有被判断为结果集的边，加入到待检索的边集合中*/
                    for(Edge e:p.getEdges()){
                        if(!resultEdge.contains(e)){
                            waitingRetrievalEdge.add(e);
                        }
                    }
                }
            }
            //加入到结果集
            resultEdge.add(minEdge);
            //从检索集合中移除
            waitingRetrievalEdge.remove(minEdge);
            /**
             * 当找到所有的点之后退出循环
             * */
            if(reachedPoint.size() >= maxPoint){
                break;
            }
        }
        return resultEdge;
    }
}
