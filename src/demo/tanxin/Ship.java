package demo.tanxin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class Ship {
    public Ship(){}
    private int minShipTime(int[] time){
        Arrays.sort(time);
        int ans=0;
        ans+=time[1]+time[0];
        int index=time.length-1;
        while(index>3){
            if((time[index]+time[index-1]+2*time[0])>(time[index]+2*time[1]+time[0]))
                ans+=time[index]+2*time[1]+time[0];
            else
                ans+=time[index]+time[index-1]+2*time[0];
            index-=2;
        }
        if(index==3){
            if((time[index]+time[index-1]+time[0])>(time[index]+2*time[1]))
                ans+=time[index]+2*time[1];
            else
                ans+=time[index]+time[index-1]+time[0];
        }
        else if(index==2)
            ans+=time[index];
        return ans;
    }

    @Test
    public void test(){
        int[] arr=new int[]{5,5,1,5,5,15,16,17};//5 1 17 5 5 1 15 1 5 1 5 1 5
        System.out.println("arr:"+ Arrays.toString(arr));
        System.out.println("minTime:"+minShipTime(arr));
        System.out.println(5+1+17+5+5+1+15+1+5+1+5+1+5);
    }
}
