package demo;

import org.junit.Test;

public class MyDui {
    int[] arr;
    boolean state=true;
    int index=0;
    MyDui(int capacity)
    {
        arr=new int[capacity+1];
    }
    MyDui(int capacity,boolean state)
    {
        arr=new int[capacity+1];
        this.state=state;
    }

    public void offer(int num)
    {
        arr[index]=num;
        int j=index;
        while(j>0)
        {
            int i=0;
            if(state){
                if(j%2==0)
                    i=(j-2)/2;
                else
                    i=(j-1)/2;
                if(arr[i]<=arr[j])
                    break;
                else{
                    int t=arr[i];
                    arr[i]=arr[j];
                    arr[j]=t;
                    j=i;
                }
            }else{
                if(j%2==0)
                    i=(j-2)/2;
                else
                    i=(j-1)/2;
                if(arr[i]>=arr[j])
                    break;
                else{
                    int t=arr[i];
                    arr[i]=arr[j];
                    arr[j]=t;
                    j=i;
                }
            }
        }
        if(index!=arr.length-1)
            index++;
    }

    public int poll()
    {
        if(index==0)
            return -1;
        int ans=arr[0];
        if(index==1){
            arr[0]=arr[1];
            arr[1]=0;
            index--;
            return ans;
        }
        int next=arr[index--];
        arr[0]=next;
        int i=0;
        while(i<index){
            if(state){
                int left=i*2+1;
                int right=i*2+2;
                if(left<index&&arr[left]<arr[i]){
                    int t=arr[i];
                    arr[i]=arr[left];
                    arr[left]=t;
                    i=left;
                }else if(right<index&&arr[right]<arr[i]){
                    int t=arr[right];
                    arr[right]=arr[i];
                    arr[i]=t;
                    i=right;
                }else
                    break;
            }else{
                int left=i*2+1;
                int right=i*2+2;
                if(left<index&&arr[left]>arr[i]){
                    int t=arr[i];
                    arr[i]=arr[left];
                    arr[left]=t;
                    i=left;
                }else if(right<index&&arr[right]>arr[i]){
                    int t=arr[right];
                    arr[right]=arr[i];
                    arr[i]=t;
                    i=right;
                }else
                    break;
            }
        }
        return ans;
    }

    public int peek()
    {
        if(index==0)
            return -1;
        return arr[0];
    }

    public int size()
    {
        return index;
    }


}
