package demo;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class test_1 {
    private volatile static test_1 single;
    private test_1 getSingle()
    {
        if(single==null)
        {
            synchronized (test_1.class)
            {
                if(single==null)
                {
                    single=new test_1();
                }
            }
        }
        return single;
    }
    public static ReentrantLock reentrantLock=new ReentrantLock();
    public static void main(String[] args) {
        //[[1,15],[7,18],[7,6],[7,100],[2,200],[17,30],[17,45],[3,5],[7,8],[3,6],[3,10],[7,20],[17,3],[17,45]]
        int[][] ans=new int[][]{{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}};
        Arrays.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0])
                    return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });
        for (int[] an : ans) System.out.println(Arrays.toString(an));
    }
}


