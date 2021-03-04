package demo;
import java.util.*;
public class FastOrder {
    public static void dfs(int[] arr,int left,int right)
    {
        if(left>=right)
            return;
        int index=indexOf(arr,left,right);
        dfs(arr,left,index-1);
        dfs(arr,index+1,right);
    }
    public static int indexOf(int[] arr,int left,int right)
    {
        int key=arr[left];
        while(left<right)
        {
            while(left<right&&arr[right]>key)
            {
                right--;
            }
            arr[left]=arr[right];
            while(left<right&&arr[left]<=key)
            {
                left++;
            }
            arr[right]=arr[left];
        }
        arr[left]=key;
        return left;
    }

    public static void main(String[] args) {
        int[] test=new int[]{1,2,3,345,1324,467,423,456,42,6543,124,5682,5367,72542457,7673567,567567,3,12,765,345,123,123,45,56,-1};
        dfs(test,0,test.length-1);
        System.out.println(Arrays.toString(test));
    }
}
