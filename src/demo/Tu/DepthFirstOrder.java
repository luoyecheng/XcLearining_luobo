package demo.Tu;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Deque<Integer> reversePost;
    public DepthFirstOrder(DiGraph G){
        pre=new LinkedList<>();
        post=new LinkedList<>();
        reversePost=new LinkedList<>();
        marked=new boolean[G.V()];
        for(int v=0;v<G.V();v++){
            if(!marked[v])
                dfs(G,v);
        }
    }

    private void dfs(DiGraph G,int v){
        pre.offer(v);
        marked[v]=true;
        for(int w:G.adj(v))
            if(!marked[w])
                dfs(G,w);
        post.offer(v);
        reversePost.offerLast(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
