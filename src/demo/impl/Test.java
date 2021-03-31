package demo.impl;

import demo.itface.Animal;

public class Test {
    public static void main(String[] args) {
       String str=" 1 and 2 or 3";
        String[] split = str.split("ans|or");
        for(String s:split)
            System.out.println(s);
    }
}
