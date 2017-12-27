package xyz.frame.demo.jdkproxy;

public class ProxyTest {  
  
    public static void main(String[] args) throws Exception {  
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  
  
//        CustomInvocationHandler handler = new CustomInvocationHandler(new HelloWorldImpl());  
        /*HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(  
                ProxyTest.class.getClassLoader(),  
                new Class[]{HelloWorld.class},  
                handler);*/ 
//        HelloWorld proxy = (HelloWorld)handler.getProxy();
        HelloWorld proxy = (HelloWorld)new CustomInvocationHandler(new HelloWorldImpl()).getProxy();
        
        proxy.sayHello("Mikan");  
    }  
  
}