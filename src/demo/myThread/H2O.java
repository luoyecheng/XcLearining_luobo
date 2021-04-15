package demo.myThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class H2O implements Runnable{
    private static final Semaphore semaphoreH=new Semaphore(2);
    private static final Semaphore semaphoreO=new Semaphore(1);
    private static final AtomicInteger count=new AtomicInteger(10);
    private static final CyclicBarrier cyclicBarrier=new CyclicBarrier(3,()->{
        count.getAndDecrement();
        if(count.get()>0)
            System.out.println("----------");
        semaphoreH.release(2);
        semaphoreO.release(1);
    });
    private final boolean state;
    H2O(boolean state){this.state=state;}
    @Override
    public void run() {
        if(state){
            while (count.get()>0)
            {
                try {
                    semaphoreH.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println('H');
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }else{
            while (count.get()>0)
            {
                try {
                    semaphoreO.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println('O');
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new H2O(true)).start();
        new Thread(new H2O(true)).start();
        new Thread(new H2O(false)).start();
    }
}

class MyH2O implements Runnable{
    public static Semaphore semaphoreH=new Semaphore(2);
    public static Semaphore semaphoreO=new Semaphore(0);
    private boolean state;
    public MyH2O(boolean state){
        this.state=state;
    }

    @Override
    public void run() {
        if(state){
            for(int i=0;i<10;i++){
                try {
                    semaphoreH.acquire(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print('H');
                semaphoreO.release(1);
            }
        }else{
            for(int i=0;i<10;i++){
                try {
                    semaphoreO.acquire(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print('O');
                System.out.print(" ");
                semaphoreH.release(2);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyH2O(true)).start();
        new Thread(new MyH2O(true)).start();
        new Thread(new MyH2O(false)).start();
    }
}
