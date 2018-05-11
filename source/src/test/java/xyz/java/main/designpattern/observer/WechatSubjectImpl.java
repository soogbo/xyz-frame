package xyz.java.main.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

import xyz.frame.json.FrameJsonUtils;

/**
 * 观察者模式：被观察者
 * 
 * @author shisp
 * @date 2018-5-11 14:04:44
 */
public class WechatSubjectImpl implements Subject {

    /**
     * 管理观察者列表 注意到这个List集合的泛型参数为Observer接口，设计原则：面向接口编程而不是面向实现编程
     */
    private List<Observer> list;
    /**
     * 消息
     */
    private Object message;

    public WechatSubjectImpl() {
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!list.isEmpty()) {
            list.remove(observer);
        }
    }

    @Override
    public void notifySubject() {
        list.forEach((e) -> {
            e.execute(message);
        });
    }

    @Override
    public void setInfomationAndNotify(Object message) {
        this.message = message;
        System.out.println("微信服务更新消息： " + FrameJsonUtils.toJson(message));
        // 消息更新，通知所有观察者
        notifySubject();
    }

}
