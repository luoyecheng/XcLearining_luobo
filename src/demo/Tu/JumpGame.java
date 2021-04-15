package demo.Tu;

import org.junit.Test;

public class JumpGame {
    public JumpGame(){}
    public int jump(int[] paths){
        if(paths.length<=1)
            return 0;
        int cur=0,ans=0;
        while(cur<paths.length){
            if(paths[cur]==0&&cur!=paths.length-1)
                return -1;
            if(cur+paths[cur]>=paths.length-1)
                break;
            int max=cur+paths[cur];
            int idx=cur;
            for(int i=cur+1;i<=cur+paths[cur];i++)
                if(paths[i]+i>max){
                    idx=i;
                    max=paths[i]+i;
                }
            cur=idx;
            ans++;
        }
        return ans;
    }

    @Test
    public void test(){
        int[] paths=new int[]{2,3,1,1};
        System.out.println(jump(paths));
    }
}
