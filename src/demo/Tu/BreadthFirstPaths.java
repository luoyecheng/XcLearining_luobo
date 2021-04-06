package demo.Tu;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int[] len;
    public BreadthFirstPaths(Graph graph, int s){
        marked=new boolean[graph.V()];
        edgeTo=new int[graph.V()];
        len=new int[graph.V()];
        this.s=s;
        bfs(graph,s);
    }
    private void bfs(Graph G,int s){
        Queue<Integer> queue=new LinkedList<>();
        marked[s]=true;
        queue.offer(s);
        int l=1;
        len[s]=0;
        queue.offer(null);
        while(!queue.isEmpty()){
            if(queue.peek()==null){
                queue.poll();
                if(queue.peek()==null)
                    break;
                l++;
                queue.offer(null);
                continue;
            }
            int v=queue.poll();
            for(int w:G.adj(v)){
                if(!marked[w]){
                    len[w]=l;
                    edgeTo[w]=v;
                    marked[w]=true;
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))   return null;
        Deque<Integer> deque=new LinkedList<>();
        for(int x=v;x!=s;x=edgeTo[x])
            deque.offerLast(x);
        deque.offerLast(s);
        return deque;
    }

    public int length(int v){
        if(!marked[v])
            return -1;
        return len[v];
    }

}
