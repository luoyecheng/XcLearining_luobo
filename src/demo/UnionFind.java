package demo;

public class UnionFind {
    private int[] id;   //分量id
    private int count; //分量数量

    public UnionFind(int N){
        count=N;
        id=new int[N];
        for(int i=0;i<N;i++)
            id[i]=i;
    }

    public int count(){return count;}

    public boolean connected(int p,int q){
        return find_quickUnion(p)==find_quickUnion(q);
    }

    //quickFind
    public int find_quickFind(int p){
        return id[p];
    }
    //quickUnion
    public int find_quickUnion(int p){
        while (p!=id[p]){
            p=id[p];
        }
        return p;
    }
    //quickFind
    public void union_quickFind(int p,int q){
        //将p，q归并到相同的分量
        int pId=find_quickFind(p);
        int qId=find_quickFind(q);
        if(pId==qId) return;
        for(int i=0;i<id.length;i++)
            if(id[i]==pId)
                id[i]=qId;
        count--;
    }
    //quickUnion
    public void union_quickUnion(int p,int q){
        int pRoot=find_quickUnion(p);
        int qRoot=find_quickUnion(q);
        if(pRoot==qRoot) return;
        id[pRoot]=qRoot;
        count--;
    }
    public static void main(String[] args) {
        UnionFind unionFind=new UnionFind(20);
        unionFind.union_quickUnion(1,2);
        unionFind.union_quickUnion(2,3);
        unionFind.union_quickUnion(3,4);
        unionFind.union_quickUnion(4,5);
        unionFind.union_quickUnion(5,6);
        System.out.println(unionFind.connected(1,6));
    }
}
