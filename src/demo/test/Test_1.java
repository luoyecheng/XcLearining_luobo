package demo.test;

import org.junit.Test;

import java.util.Arrays;

public class Test_1 {
    public Test_1(){}

    public int palindromePartition(String s, int k) {
        int len = s.length();
        int[][] modify = new int[len][len];  // modify[i][j]为s从i到j位置子串的最少修改次数
        for (int i = 0; i < len; i++) {
            int l = i, r = i, count = 0;
            while (l >=0 && r < len) {   // 奇数长度
                if (s.charAt(l) != s.charAt(r)) count++;
                modify[l--][r++] = count;
            }
            l = i;
            r = i+1;
            count = 0;
            while (l >=0 && r < len) {  // 偶数长度
                if (s.charAt(l) != s.charAt(r)) count++;
                modify[l--][r++] = count;
            }
        }
        int[][] dp = new int[len+1][k+1]; // dp[i][j]为前i个字符的子串划分成j个子串的最小修改次数
        for (int i = 1; i <= len; i++) {
            dp[i][1] = modify[0][i-1];  // 无需划分，初始化
            for (int j = 2; j <= k && j <= i; j++) {
                dp[i][j] = 101;
                for (int p = i-1; p >= j-1; p--) {
                    dp[i][j] = Math.min(dp[i][j], modify[p][i-1] + dp[p][j-1]);
                }
            }
        }
        return dp[len][k];
    }

    public int[][] fangzhen(int n){
        int[][] ans=new int[(int)Math.pow(2,n)][(int)Math.pow(2,n)];
        ans[ans.length-1][ans.length-1]=1;
        int cur=0;
        while(cur<n){
            int row= (int) Math.pow(2,cur);
            for(int i=ans.length-row;i<ans.length;i++){
                for(int j=ans.length-2*row;j<ans.length-row;j++){
                    ans[i][j]=ans[i][j+row];
                    ans[j][i]=ans[j+row][i];
                }
            }
            cur++;
        }
        return ans;
    }

    @Test
    public void test(){
        int[][] fangzhen = fangzhen(11);
        System.out.println(Arrays.deepToString(fangzhen));
    }
}
