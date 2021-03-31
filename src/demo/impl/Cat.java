package demo.impl;

import demo.itface.Animal;

public class Cat implements Animal {
    @Override
    public void shout() {
        System.out.println("miao");
    }
}
