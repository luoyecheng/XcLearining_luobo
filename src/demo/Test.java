package demo;

public class Test {
    public static void main(String[] args) {
        MyDui myDui=new MyDui(10,false);
        for(int i=1;i<=40;i++){
            myDui.offer(i);
            System.out.println("size:"+myDui.size());
            System.out.println("peek:"+myDui.peek());
        }
    }
}
