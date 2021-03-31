package demo.staticFactory;

public class WechatProducer implements IProducer{
    @Override
    public ISender produce() {
        return new WeChat();
    }
}
