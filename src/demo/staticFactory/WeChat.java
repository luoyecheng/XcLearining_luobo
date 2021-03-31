package demo.staticFactory;

public class WeChat implements ISender{
    @Override
    public void sned() {
        System.out.println("this is WeChat");
    }
}
