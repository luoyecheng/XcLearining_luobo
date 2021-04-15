package demo.myList;

import java.util.Random;

public class SkipList <T>{
    private SkipListNode<T> head,tail;
    private int nodes;          //节点综述
    private int listLevel;      //层数
    private Random random;      //用于投掷硬币
    private static final double PROBABILITY=0.5;    //向上提升一级的概率

    public SkipList(){
        random=new Random();
    }

    //清空跳表
    public void clear(){
        head=new SkipListNode<T>(SkipListNode.HEAD_KEY,null);
        tail=new SkipListNode<T>(SkipListNode.TAIL_KEY,null);
        horizontailLink(head,tail);
        listLevel=0;
        nodes=0;
    }

    public boolean isEmpty(){
        return nodes==0;
    }

    public int size(){return nodes;}

    //在最下面一层，找到要插入的位置前面的那个key
    private SkipListNode<T> findNode(int key){
        SkipListNode<T> p=head;
        while(true){
            while(p.right.key!=SkipListNode.TAIL_KEY&&p.right.key<=key)
                p=p.right;
            if(p.down!=null)
                p=p.down;
            else
                break;
        }
        return p;
    }

    //查找是否存储key，存在则返回该节点 否则返回null
    public SkipListNode<T> search(int key){
        SkipListNode<T> p=findNode(key);
        if(key==p.getKey())
            return p;
        return null;
    }

    //向跳跃表中添加key-value
    public void put(int k,T v){
        SkipListNode<T> p=findNode(k);
        //如果key值相同，替换原来的value
        if(k==p.getKey()){
            p.value=v;
            return;
        }
        SkipListNode<T> q=new SkipListNode<>(k,v);
        breajLink(p,q);
        int currentLevel=0;//当期所在的层级为0
        //抛硬币
        while(random.nextDouble()<PROBABILITY){
            //如果超过了高度 需要重新建一个层级
            if(currentLevel>=listLevel){
                listLevel++;
                SkipListNode<T> p1=new SkipListNode<>(SkipListNode.HEAD_KEY,null);
                SkipListNode<T> p2=new SkipListNode<>(SkipListNode.TAIL_KEY,null);
                horizontailLink(p1,p2);
                verticalLink(p1,head);
                verticalLink(p2,tail);
                head=p1;
                tail=p2;
            }
            //将p移动到上一层
            while(p.up!=null)
                p=p.left;
            p=p.up;

            SkipListNode<T> e=new SkipListNode<>(k,null);//只保存key就可以
            breajLink(p,e);
            verticalLink(e,q);  //将e和q上下连接
            q=e;
            currentLevel++;
        }
        nodes++;//层数递增
    }

    //node1后面插入node2
    private void breajLink(SkipListNode<T> node1,SkipListNode<T> node2){
        node1.right.left=node2;
        node2.right=node1.right;
        node1.right=node2;
        node2.left=node1;
    }

    //水平双向连接
    private void horizontailLink(SkipListNode<T> node1,SkipListNode<T> node2){
        node1.right=node2;
        node2.left=node1;
    }

    //垂直双向连接
    private void verticalLink(SkipListNode<T> node1,SkipListNode<T> node2){
        node1.down=node2;
        node2.up=node1;
    }
}
