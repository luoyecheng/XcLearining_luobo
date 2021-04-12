package demo.IndexMinQueue;

import java.util.*;

public class MyIndexMinPriorityQueue {
    private Map<Integer,Double> map;
    private TreeMap<Double, Set<Integer>> treeMap;
    public MyIndexMinPriorityQueue(){
        map=new HashMap<>();
        treeMap=new TreeMap<>();
    }

    public boolean isEmpty(){
        return map.size()==0;
    }

    public void insert(int key,double value){
        map.put(key,value);
        if(treeMap.containsKey(value))
            treeMap.get(value).add(key);
        else {
            Set<Integer> set=new HashSet<>();
            set.add(key);
            treeMap.put(value,set);
        }
    }
    public boolean contains(int key){return map.containsKey(key);}

    public void changeItem(int key,double value){
        Double oldValue = map.get(key);
        map.put(key,value);
        treeMap.get(oldValue).remove(key);
        if(treeMap.get(oldValue).size()==0)
            treeMap.remove(oldValue);
        if(treeMap.containsKey(value))
            treeMap.get(value).add(key);
        else{
            Set<Integer> set=new HashSet<>();
            set.add(key);
            treeMap.put(value,set);
        }
    }

    public int delMin(){
        Set<Integer> set = treeMap.get(treeMap.firstKey());
        Integer key = set.iterator().next();
        set.remove(key);
        if(set.size()==0)
            treeMap.remove(treeMap.firstKey());
        map.remove(key);
        return key;
    }
}
