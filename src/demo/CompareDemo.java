package demo;

public class CompareDemo implements Comparable<CompareDemo>{
    private int year;
    private int month;
    private int day;
    @Override
    public int compareTo(CompareDemo that) {
        if(this.year>that.year)
            return 1;
        if(this.year<that.year)
            return -1;
        if(this.month> that.month)
            return 1;
        if (this.month< that.month)
            return -1;
        return Integer.compare(this.day, that.day);
    }

    public static void main(String[] args) {

    }
}
