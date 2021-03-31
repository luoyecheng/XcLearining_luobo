package demo;

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
