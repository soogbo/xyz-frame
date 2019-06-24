package xyz.java.main.designpattern.observer;

/**
 * 观察者模式：抽象被观察者接口
 * 
 * @author shisp
 * @date 2018-5-11 14:04:44
 */
public interface Subject {

    /**
     * 注册观察者
     * 
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * 
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifySubject();

    /**
     * 获取信息，进行通知
     * 
     * @param message
     */
    void setInfomationAndNotify(Object message);

}
