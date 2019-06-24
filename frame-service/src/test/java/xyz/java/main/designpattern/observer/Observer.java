package xyz.java.main.designpattern.observer;

/**
 * 观察者模式：抽象观察者接口
 * 
 * @author shisp
 * @date 2018-5-11 14:04:44
 */
public interface Observer {

    /**
     * @param message
     *            被观察者回调
     */
    void execute(Object message);
}
