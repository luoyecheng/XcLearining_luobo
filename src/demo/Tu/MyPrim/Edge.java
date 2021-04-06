package demo.Tu.MyPrim;

public class Edge {
    private Point[] point= new Point[2];

    /** 权 */
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Point[] getPoint() {
        return point;
    }

    public void setPoint(Point[] point) {
        this.point = point;
    }

}
