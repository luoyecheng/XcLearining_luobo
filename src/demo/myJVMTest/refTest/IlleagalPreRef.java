package demo.myJVMTest.refTest;

import org.junit.Test;

public class IlleagalPreRef {
    public IlleagalPreRef(){}
    static {
        i=100;
        System.out.println("change");
    }
    public static int i=1;

    @Test
    public void test(){
        System.out.println(i);
    }

}
