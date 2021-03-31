package demo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class a2b  implements Runnable{
    private static final Semaphore semaphore1=new Semaphore(1);
    private static final Semaphore semaphore2=new Semaphore(0);
    private static final Semaphore semaphore3=new Semaphore(0);
    private int state;
    private int count1;
    private int count2;
    private int count3;
    a2b(int state,int count1,int count2,int count3)
    {
        this.state=state;
        this.count1=count1;
        this.count2=count2;
        this.count3=count3;
    }

    @Override
    public void run() {
        if(state==0)
        {
            while(count1-->0)
            {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"====== a "+count1);
                semaphore2.release();
            }
        }else if(state==1){
            while(count2-->0)
            {
                try {
                    semaphore2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"====== b "+count2);
                semaphore3.release();
            }
        }else{
            while(count3-->0)
            {
                try {
                    semaphore3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"====== c "+count3);
                semaphore1.release();
            }
        }
    }

    public static void main(String[] args) {
        a2b test1=new a2b(0,102,102,102);
        a2b test2=new a2b(1,102,102,102);
        a2b test3=new a2b(2,102,102,102);
        new Thread(test1).start();
        new Thread(test2).start();
        new Thread(test3).start();
    }
}
