package demo.Tu.MyPrim;

import java.util.HashSet;
import java.util.Set;

public class Point {
    private Set<Edge> edges=new HashSet<>();

    public Set<Edge> getEdges(){return edges;}

    public Point addEdge(Edge edge){
        edges.add(edge);
        return this;
    }
}
