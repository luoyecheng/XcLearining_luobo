package demo;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedQuickUnionFind {
    private int[] id; //父链接数组
    private int[] sz; //各个根节点对应的分量的大小
    private int count; //连通分量的数量

    public WeightedQuickUnionFind(int N){
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++)
            id[i]=i;
        sz=new int[N];
        Arrays.fill(sz,1);
    }

    public int count(){return  count;}

    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    public int find(int p){
        int r=p;
        while(r!=id[r]) r=id[r];
        //路径压缩
        int i=p,j;
        while(i!=r){
            j=id[i];
            id[i]=r;
            i=j;
        }
        return r;
    }

    public void union(int p,int q){
        int i=find(p);
        int j=find(q);
        if(i==j)    return;
        //将小树的根节点连接到大树的根节点
        if(sz[i]<sz[j]){
            id[i]=j;
            sz[j]+=sz[i];
        }else{
            id[j]=id[i];
            sz[j]+=sz[i];
        }
        count--;
    }

    public static void main(String[] args) {
        WeightedQuickUnionFind weightedQuickUnionFind=new WeightedQuickUnionFind(20);
        for(int i=0;i<10;i++)
            weightedQuickUnionFind.union(i,i+1);
        System.out.println(weightedQuickUnionFind.count());

    }
}
