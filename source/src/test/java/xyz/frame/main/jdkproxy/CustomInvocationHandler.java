package xyz.frame.main.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CustomInvocationHandler implements InvocationHandler {  
    private Object target;  
  
    public CustomInvocationHandler(Object target) {  
        this.target = target;  
    }  
  
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
        System.out.println("Before invocation");  
        Object retVal = method.invoke(target, args);  
        System.out.println("After invocation");  
        return retVal;  
    }  
    
    
    /** 
     * 获取目标对象的代理对象 
     * @return 代理对象 
     */  
    public Object getProxy() {  
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),   
                target.getClass().getInterfaces(), this);  
    }
} 