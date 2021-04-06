package demo.Tu;

public class Topological {
    private Iterable<Integer> order;
    public Topological(DiGraph G){
        DirectedCycle cyclefinder=new DirectedCycle(G);
        if(!cyclefinder.hasCycle()){
            DepthFirstOrder dfs=new DepthFirstOrder(G);
            order=dfs.reversePost();
        }
    }
    public Iterable<Integer> order(){
        return order;
    }
    public boolean isDAG(){
        return order!=null;
    }

}
