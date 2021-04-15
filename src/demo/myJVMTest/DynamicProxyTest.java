package demo.myJVMTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello{
        void sayHello(String name);
    }

    static class Hello implements IHello{

        @Override
        public void sayHello(String name) {
            System.out.println("hello "+name);
        }
    }

    static class DynamicProxy implements InvocationHandler{
        Object originalObj;

        Object bind(Object originalObj){
            this.originalObj=originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),originalObj.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome to my house");
            if(args!=null&&args.length>0){
                String name= (String) args[0];
                name=name.toUpperCase();
                args[0]=name;
            }
            return method.invoke(originalObj,args);
        }
    }

    public static void main(String[] args) {
        IHello hello= (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello("luobo");
        System.out.println(hello.toString());
        System.out.println(hello.hashCode() );
    }
}
