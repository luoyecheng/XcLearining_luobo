package demo.myThread;

import java.util.concurrent.Semaphore;

public class Demo01020304 implements Runnable{
    private static final Semaphore semaphore1=new Semaphore(1);
    private static final Semaphore semaphore2=new Semaphore(1);
    private static final Semaphore semaphore3=new Semaphore(0);
    private static int count;
    private static int num=1;
    private int state;
    Demo01020304(int state,int count)
    {
        this.state=state;
        Demo01020304.count =count;
    }
    @Override
    public void run() {
        if(state==0){
            while(num<count)
            {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"=== 0");
                semaphore2.release();
                semaphore3.release();
            }
        }else if(state==1){
            while(num<count)
            {
                try {
                    semaphore2.acquire(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"=== "+num);
                if(num!=count)
                    semaphore1.release();
                num++;
            }
        }else{
            while(num<count)
            {
                try {
                    semaphore3.acquire(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"=== "+num);
                if(num!=count)
                    semaphore1.release();
                num++;
            }
        }
    }

    public static void main(String[] args) {
        Demo01020304 d1=new Demo01020304(0,100);
        Demo01020304 d2=new Demo01020304(1,100);
        Demo01020304 d3=new Demo01020304(2,99);
        new Thread(d1).start();
        new Thread(d2).start();
        new Thread(d3).start();
    }
}
