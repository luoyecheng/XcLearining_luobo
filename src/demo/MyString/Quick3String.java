package demo.MyString;

public class Quick3String {
    private static int charAt(String s,int d){
        if(d<s.length())
            return s.charAt(d);
        return -1;
    }

    public static void sort(String[] a,int lo,int hi,int d){
        if(hi<=lo)
            return;
        int lt=lo,gt=hi;
        int v=charAt(a[lo],d);
        int i=lo+1;
        while (i<=gt){
            int t=charAt(a[i],d);
            if(t<v)
                exch(a,lt++,i++);
            else if(t>v)
                exch(a,i,gt--);
            else
                i++;
        }
        sort(a,lo,lt-1,d);
        if(v>=0)
            sort(a,lt,gt,d+1);
        sort(a,gt+1,hi,d);
    }

    private static void exch(String[] a,int i,int j){
        String tamp=a[i];
        a[i]=a[j];
        a[j]=tamp;
    }

    public static void main(String[] args) {
        String[] a=new String[4];
        a[0]="adwqdqw";
        a[1]="5324423";
        a[2]="geynwgfw";
        a[3]="pojjiewq";
        sort(a,0,3,3);
        for (String s : a) {
            System.out.println(s);
        }
    }
}
