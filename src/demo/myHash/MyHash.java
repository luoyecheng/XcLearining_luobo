package demo.myHash;


import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyHash {
    public MyHash(){}
    private int hash(int[] num){
        int[] fac=new int[num.length-1];
        fac[0]=1;
        for(int i=1;i<fac.length;i++)
            fac[i]=fac[i-1]*(i+1);
        int key=0;
        for(int i=num.length-2;i>=0;i--){
            int count=0;
            for(int j=i+1;j<num.length;j++)
                if(num[j]<num[i])
                    count++;
            key+=count*fac[num.length-2-i];
        }
        return key;
    }

    private int[] cantor(int rank,int k){
        int[] fac=new int[k-1];
        fac[0]=1;
        for(int i=1;i<fac.length;i++)
            fac[i]=fac[i-1]*(i+1);
        int index=fac.length-1;
        int[] ans=new int[k];
        List<Integer> list=new LinkedList<>();
        for(int i=1;i<=k;i++)
            list.add(i-1);
        for(int i=0;i<k-1;i++){
            int num=rank/fac[index];
            ans[i]=list.get(num);
            list.remove(num);
            rank%=fac[index];
            index--;
        }
        ans[k-1]=list.get(0);
        return ans;
    }

    private int[] cantor_new(int rank,int k){
        int[] fac=new int[k-1];
        fac[0]=1;
        for(int i=1;i<fac.length;i++)
            fac[i]=fac[i-1]*(i+1);
        int index=fac.length-1;
        int[] ans=new int[k];
        boolean[] state=new boolean[k];
        Arrays.fill(state,true);
        for(int i=0;i<k-1;i++){
            int num=rank/fac[index];
            int idx=0;
            while(num>0){
                if(state[idx])
                    num--;
                idx++;
            }
            while(!state[idx])
                idx++;
            ans[i]=idx;
            state[idx]=false;
            rank%=fac[index];
            index--;
        }
        for(int i=0;i<state.length;i++)
            if(state[i]){
                ans[ans.length-1]=i;
                break;
            }
        return ans;
    }

    @Test
    public void test(){
        System.out.println(Arrays.toString(cantor(35, 9)));
        System.out.println(Arrays.toString(cantor_new(35, 9)));
    }
}
