package demo.impl;

import demo.itface.Animal;

public class Dog implements Animal {
    @Override
    public void shout() {
        System.out.println("wang!");
    }
}
