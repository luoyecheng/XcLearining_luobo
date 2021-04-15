package demo.myJVMTest;

public class StaticDispatch {
    static abstract class Human{}
    static class Man extends Human{}
    static class Woman extends Human{}
    public static void hello(Human human){
        System.out.println("hello guy");
    }
    public void hello(Man man){
        System.out.println("hello man");
    }
    public void hello(Woman woman){
        System.out.println("hello woman");
    }

    public static void main(String[] args) {
        int[][] arr=new int[-1][-1];
    }
}
