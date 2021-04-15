package demo.sort;

import java.util.Arrays;

public class GuibinPaiXu {
    public static void merge(int[] arr,int left,int mid,int right){
        int indexI=left,indexJ=mid+1,index=left;
        int[] temp=new int[arr.length];
        while(indexI<=mid&&indexJ<=right)
        {
            if(arr[indexI]<arr[indexJ])
                temp[index++]=arr[indexI++];
            else
                temp[index++]=arr[indexJ++];
        }
        while(indexI<=mid){
            temp[index++]=arr[indexI++];
        }
        while(indexJ<=right){
            temp[index++]=arr[indexJ++];
        }
        for(int i=left;i<=right;i++)
            arr[i]=temp[i];
    }

    public static void split(int[] arr,int left,int right){
        if(left==right)
            return;
        int mid=left+(right-left)/2;
        split(arr,left,mid);
        split(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    public static void main(String[] args) {
        int[] ans=new int[]{11,45,432,765,234,4536,412,456,41,456,98,12};
        split(ans,0,ans.length-1);
        System.out.println(Arrays.toString(ans));
    }
}
