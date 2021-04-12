package demo.myHash;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BaShuMa {
    private int[] fac;
    private boolean[] state;
    private int[] arr;
    private int idx0;

    public BaShuMa(){}

    public void init(int[] init){
        fac=new int[init.length-1];
        state=new boolean[init.length];
        arr=new int[init.length];
        for(int i=0;i<init.length;i++){
            arr[i]=init[i];
            if(init[i]==0)
                idx0=i;
        }
        fac[0]=1;
        for(int i=1;i<fac.length;i++)
            fac[i]=fac[i-1]*(i+1);
    }

    public int getIdx0(){return idx0;}

    public int hash(){
        int key=0;
        for(int i=arr.length-2;i>=0;i--){
            int count=0;
            for(int j=i+1;j<arr.length;j++)
                if(arr[j]<arr[i])
                    count++;
            key+=count*fac[arr.length-2-i];
        }
        return key;
    }

    public int[] cantor(int rank){
        Arrays.fill(state,true);
        int index=fac.length-1;
        for(int i=0;i<arr.length-1;i++){
            int num=rank/fac[index];
            int idx=0;
            while(num>0){
                if(state[idx])
                    num--;
                idx++;
            }
            while(!state[idx])
                idx++;
            arr[i]=idx;
            state[idx]=false;
            rank%=fac[index];
            index--;
        }
        for(int i=0;i<state.length;i++)
            if(state[i]){
                arr[arr.length-1]=i;
                break;
            }
        for(int i=0;i<arr.length;i++)
            if(arr[i]==0){
                idx0=i;
                break;
            }
        return arr;
    }

    public int minStep(int[] start,int[] end){
        int num=1;
        for(int i=2;i<=start.length;i++)
            num*=i;
        boolean[] hasSearched=new boolean[num];
        BaShuMa baShuMa=new BaShuMa();
        baShuMa.init(start);
        int begin=baShuMa.hash();
        baShuMa.init(end);
        int target=baShuMa.hash();
        Queue<Integer> q1=new LinkedList<>();
        Queue<Integer> q2=new LinkedList<>();
        q1.offer(begin);
        int ans=0;
        int[] append=new int[]{-3,-1,1,3};
        while(!q1.isEmpty()||!q2.isEmpty()){
            if(!q1.isEmpty()){
                while(!q1.isEmpty()){
                    int n=q1.poll();
                    hasSearched[n]=true;
                    if(n==target)
                        return ans;
                    int[] cantor = baShuMa.cantor(n);
                    int idx0 = baShuMa.getIdx0();
                    for(int a:append){
                        if(idx0%3==0&&a==-1)
                            continue;
                        if(idx0%3==2&&a==1)
                            continue;
                        if(idx0+a<cantor.length&&idx0+a>=0){
                            int temp=cantor[idx0+a];
                            cantor[idx0+a]=cantor[idx0];
                            cantor[idx0]=temp;
                            int hash = baShuMa.hash();
                            if(!hasSearched[hash])
                                q2.offer(hash);
                            temp=cantor[idx0+a];
                            cantor[idx0+a]=cantor[idx0];
                            cantor[idx0]=temp;
                        }
                    }
                }
            }else{
                while(!q2.isEmpty()){
                    int n=q2.poll();
                    hasSearched[n]=true;
                    if(n==target)
                        return ans;
                    int[] cantor = baShuMa.cantor(n);
                    int idx0 = baShuMa.getIdx0();
                    for(int a:append){
                        if(idx0+a<cantor.length&&idx0+a>=0){
                            int temp=cantor[idx0+a];
                            cantor[idx0+a]=cantor[idx0];
                            cantor[idx0]=temp;
                            int hash = baShuMa.hash();
                            if(!hasSearched[hash])
                                q1.offer(hash);
                            temp=cantor[idx0+a];
                            cantor[idx0+a]=cantor[idx0];
                            cantor[idx0]=temp;
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    @Test
    public void test(){
        int[] start=new int[]{0,1,2,3,4,5,6,7,8};
        int[] end=new int[]{7,5,2,1,4,0,6,8,3};

        int step = minStep(start, end);
        System.out.println(step);
    }

}
