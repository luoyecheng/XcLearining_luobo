package demo.tanxin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class WorkTime {
    public WorkTime(){}

    public int longgestWorkTime(int[][] arr){
        if(arr[0].length!=2)
            throw new RuntimeException("输入参数不对 int[][] arr=new int[m][2]");
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int ans=1;
        int j=0;
        for(int i=0;i<arr.length;i++)
            if(arr[i][0]>=arr[j][1]){
                ans++;
                j=i;
            }
        return ans;
    }

    @Test
    public void test(){
        int[][] arr=new int[][]{{1,4},{3,5},{0,6},{5,7},{3,8},{5,9},{6,10},{8,11},{8,12},{2,13},{12,14}};
        System.out.println(longgestWorkTime(arr));
    }
}
