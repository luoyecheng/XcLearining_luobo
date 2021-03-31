package demo;

import java.util.ArrayList;
import java.util.List;

public class DeadLock implements Runnable {
    public static Object o1=new Object();
    public static Object o2=new Object();
    boolean flag;
    DeadLock(boolean flag){
        this.flag=flag;
    }
    @Override
    public void run() {
        if(flag)
        {
            synchronized (o1){
                System.out.println("one-1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("one-2");
                }
            }
        }else {
            synchronized (o2){
                System.out.println("two-1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("two-2");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock d1=new DeadLock(true);
        DeadLock d2=new DeadLock(false);
        new Thread(d1).start();
        new Thread(d2).start();
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
    }
}
