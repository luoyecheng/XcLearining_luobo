package demo;

import org.junit.Test;

public class MyTest {
    public int max(int sum,int num){
        if(num==2)
            return 0;
        if(num==3)
            return sum;
        int ans=0;
        for(int i=4;i<=num;i++)
            ans+=(i/2);
        return sum-ans;
    }

    @Test
    public void test_1(){
        System.out.println(max(1000,20));

    }

}
