package xyz.java.main.designpattern.strategy;

// 具体的策略实现
public class ConcreteStrategy implements IStrategy {
    
    // 具体的算法实现
    @Override
    public void algorithmMethod() {
        System.out.println("this is ConcreteStrategy method...");
    }
}