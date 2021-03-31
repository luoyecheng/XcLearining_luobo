package demo.buildermode;

public class CarBuilder implements Builder{
    @Override
    public String buildEngine() {
        return "Engine";
    }

    @Override
    public String buildTyre() {
        return "Tyre";
    }

    @Override
    public String buildSeat() {
        return "Seat";
    }
}
