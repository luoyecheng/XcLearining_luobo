package demo.buildermode;

import java.util.*;

public class CarDirector {
    CarBuilder cb;

    public CarDirector(CarBuilder cb) {
        this.cb = cb;
    }

    public Car constructCar(){
        Car car=new Car();
        car.setEngine(cb.buildEngine());
        car.setSeat(cb.buildSeat());
        car.setTyre(cb.buildTyre());
        return car;
    }

    public static void main(String[] args) {
    }
}
