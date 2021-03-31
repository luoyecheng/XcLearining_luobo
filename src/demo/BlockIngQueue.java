package demo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockIngQueue {
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition freeCond=lock.newCondition();
    private final Condition elemCond=lock.newCondition();
    private final Queue<Integer> queue=new LinkedList<>();
    private final int capacity;

    public BlockIngQueue(int capacity) {
        this.capacity=capacity;
    }
    public void enqueue(int element)
    {
        lock.lock();
        try{
            while (queue.size()==capacity){
                freeCond.await();
            }
            queue.offer(element);
            elemCond.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public int dequeue()
    {
        lock.lock();
        int num=-1;
        try{
            while(queue.size()==0)
            {
                elemCond.await();
            }
            num=queue.poll();
            freeCond.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return num;
    }

    public int size()
    {
        lock.lock();
        try {
            return queue.size();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockIngQueue queue=new BlockIngQueue(10);
        new Thread(()->{
            for(int i=0;i<30;i++){
                queue.enqueue(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+" "+queue.dequeue());
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+" "+queue.dequeue());
            }
        }).start();
    }
}
