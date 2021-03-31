package demo;

public class Factory {
    public interface Proj{
        void sned();
    }
    public class ProjA implements Proj{
        @Override
        public void sned() {
            System.out.println("this is project A");
        }
    }

    public class ProjB implements Proj{
        @Override
        public void sned() {
            System.out.println("this is project B");
        }
    }

    public Proj getInstance(Class c){
        if(c.equals(ProjA.class))
            return new ProjA();
        if(c.equals(ProjB.class))
            return new ProjB();
        return null;
    }

    public static void main(String[] args) {
        Factory factory=new Factory();
        Proj instance = factory.getInstance(ProjB.class);
        instance.sned();
    }

}
