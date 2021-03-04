package demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CyclicBarrierExample {
    public static void main(String[] args) throws Throwable {
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        System.out.println(entries.size());
        map.put(4,4);
        System.out.println(entries.size());
    }
}
