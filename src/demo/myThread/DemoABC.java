package demo.myThread;

public class DemoABC implements Runnable{
    private static volatile int state=0;
    private final int type;
    DemoABC(int type)
    {
        this.type=type;
    }
    @Override
    public void run() {
        if(type==0){
            while(state!=0){
                Thread.yield();
            }
            System.out.println('A');
            state++;
        }else if(type==1){
            while(state!=1){
                Thread.yield();
            }
            System.out.println('B');
            state++;
        }else{
            while(state!=2){
                Thread.yield();
            }
            System.out.println('C');
        }
    }

    public static void main(String[] args) {
        DemoABC d1=new DemoABC(0);
        DemoABC d2=new DemoABC(1);
        DemoABC d3=new DemoABC(2);
        new Thread(d3).start();
        new Thread(d1).start();
        new Thread(d2).start();
    }
}
