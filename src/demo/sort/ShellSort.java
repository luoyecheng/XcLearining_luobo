package demo.sort;

import java.util.*;

public class ShellSort {
    public static void sort(Comparable[] a){
        int N=a.length;
        int h=1;
        while(h<N/3)    h=3*h+1;
        while(h>=1){
            for(int i=h;i<N;i++){
                for(int j=i;j>=h&&a[j].compareTo(a[j-h])<0;j-=h){
                    Comparable temp=a[j];
                    a[j]=a[j-h];
                    a[j-h]=temp;
                }
            }
            h/=3;
        }
    }
//    43261596 (00000010100101000001111010011100)
    public static void main(String[] args) {
        int num=43261596;
        String str = Integer.toBinaryString(num);
        System.out.println(str);
        System.out.println(str.length());
        System.out.println(Integer.reverse(num));
    }
}
