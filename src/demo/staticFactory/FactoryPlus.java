package demo.staticFactory;

public class FactoryPlus {
    public static void main(String[] args) {
        IProducer factory=new TimProducer();
        factory.produce().sned();
    }
}
