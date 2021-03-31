package demo;

import org.junit.Test;

import java.util.*;

public class BFS {
    public int call(int[][] nums,int from,int to){
        Map<Integer,Set<Integer>> map=new HashMap<>();
        for(int[] num:nums){
            if(map.containsKey(num[0]))
                map.get(num[0]).add(num[1]);
            else{
                Set<Integer> set=new HashSet<>();
                set.add(num[1]);
                map.put(num[0],set);
            }
        }
        int ans=1;
        if(!map.containsKey(from))
            return -1;
        Set<Integer> set = new HashSet<>(map.get(from));
        map.remove(from);
        while(set.size()!=0){
            if(set.contains(to))
                return ans;
            ans++;
            Set<Integer> temp=new HashSet<>();
            for(Integer i:set)
                if(map.containsKey(i))
                    temp.addAll(map.get(i));
            set=temp;
        }
        return -1;
    }
    @Test
    public void test(){
        int[][] t=new int[50][2];
        for(int i=0;i<50;i++){
            t[i][0]=i;
            t[i][1]=i*2;
        }
        System.out.println(call(t,3,96));
    }

    @Test
    public void Test_Map(){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<10;i++)
            map.put(i,i);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

    @Test
    public void test_1(){
    }
}
