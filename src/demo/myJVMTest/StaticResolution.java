package demo.myJVMTest;

public class StaticResolution {
    public static void sayHello(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        StaticResolution.sayHello();
    }
}
