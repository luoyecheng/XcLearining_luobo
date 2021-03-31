package demo;

import java.util.HashMap;
import java.util.Map;

public class MyLru<K,V> {
    static class Node<K,V>
    {
        K key;
        V value;
        Node<K,V> pre;
        Node<K,V> pos;
        Node(K key,V value){
            this.key=key;
            this.value=value;
        }
        Node(){}
    }
    private final int size;
    private final Node<K,V> head;
    private final Node<K,V> tail;
    private final Map<K,Node<K,V>> map;
    MyLru(int size)
    {
        map=new HashMap<>();
        this.size=size;
        head=new Node<K,V>();
        tail=new Node<K,V>();
        head.pos=tail;
        tail.pre=head;
    }
    private void moveAhead(Node<K,V> node)
    {
        node.pre.pos=node.pos;
        node.pos.pre=node.pre;
        head.pos.pre=node;
        node.pos=head.pos;
        head.pos=node;
        node.pre=head;
    }

    public void put(K key,V value)
    {
        if(!map.containsKey(key)){
            Node<K,V> node=new Node<>(key, value);
            node.pos=head.pos;
            head.pos.pre=node;
            node.pre=head;
            head.pos=node;
            map.put(key,node);
            if(map.size()>size)
            {
                map.remove(tail.pre.key);
                tail.pre.pre.pos=tail;
                tail.pre=tail.pre.pre;
            }
        }else{
            Node<K,V> node=map.get(key);
            node.value=value;
            moveAhead(node);
        }
    }

    public V get(K key)
    {
        if(!map.containsKey(key))
            return null;
        Node<K,V> node=map.get(key);
        moveAhead(node);
        return node.value;
    }

    public int size(){return map.size();}

    public static void main(String[] args) {
        MyLru<Integer,Integer> myMap=new MyLru<>(4);
        myMap.put(1,1);
        myMap.put(2,2);
        myMap.put(3,3);
        myMap.put(4,4);
        myMap.put(5,5);
        myMap.put(2,-2);
        myMap.put(6,6);
        System.out.println(myMap.size());
        System.out.println(myMap.get(1));
        System.out.println(myMap.get(2));
        System.out.println(myMap.get(3));
    }
}
