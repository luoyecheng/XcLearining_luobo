package demo.staticFactory;

public class TimProducer implements IProducer{
    @Override
    public ISender produce() {
        return new Tim();
    }
}
