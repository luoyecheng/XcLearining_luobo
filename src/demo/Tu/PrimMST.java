package demo.Tu;


import java.util.PriorityQueue;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private PriorityQueue<Double> pq;
    public PrimMST(){
        pq=new PriorityQueue<>();

    }
}
