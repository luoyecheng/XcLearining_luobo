package demo.Tu;

import demo.Tu.BreadthFirstPaths;
import demo.Tu.Graph;
import org.junit.Test;

import java.util.Random;

public class HotelPath {
    public int minHotelPath(int[][] path,int a,int b,int c,int n){
        Graph G=new Graph(n);
        for(int[] num:path)
            G.addEdge(num[0],num[1]);
        BreadthFirstPaths bfsA=new BreadthFirstPaths(G,a);
        BreadthFirstPaths bfsB=new BreadthFirstPaths(G,b);
        BreadthFirstPaths bfsC=new BreadthFirstPaths(G,c);
        int ans=3*n;
        int index=0;
        for(int i=0;i<n;i++){
            int temp=0;
            int aa = bfsA.length(i);
            int bb = bfsB.length(i);
            int cc = bfsC.length(i);
            if(aa<0||bb<0||cc<0)
                continue;
            temp=aa+bb+cc;
            if(temp<ans){
                ans=temp;
                index=i;
                System.out.println("i:"+i+" ans:"+ans);
            }
        }
        Iterable<Integer> iterableA = bfsA.pathTo(index);
        Iterable<Integer> iterableB = bfsB.pathTo(index);
        Iterable<Integer> iterableC = bfsC.pathTo(index);
        System.out.println(iterableA);
        System.out.println(iterableB);
        System.out.println(iterableC);
        System.out.println(bfsA.length(index));
        System.out.println(bfsB.length(index));
        System.out.println(bfsC.length(index));
        return ans;
    }
    @Test
    public void test() {
        int[][] path=new int[3000][2];
        Random random=new Random();
        for(int i=0;i<3000;i++){
            path[i][0]=random.nextInt(1000);
            path[i][1]=random.nextInt(1000);
        }
        int ans = minHotelPath(path, 23, 30, 168, 1000);
        System.out.println(ans);
    }
}
