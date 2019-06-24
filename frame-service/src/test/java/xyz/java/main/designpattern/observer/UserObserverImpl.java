package xyz.java.main.designpattern.observer;

import xyz.frame.json.FrameJsonUtils;

/**
 * 观察者模式：观察者
 * 
 * @author shisp
 * @date 2018-5-11 14:04:44
 */
public class UserObserverImpl implements Observer {

    /**
     * 消息
     */
    private Object message;

    /**
     * 观察者属性
     */
    private String name;

    public UserObserverImpl(String name) {
        this.name = name;
    }
    public UserObserverImpl(Object message, String name) {
        this(name);
        this.message = message;
    }

    @Override
    public void execute(Object message) {
        this.message = message;
        update();
    }

    /**
     * 处理观察获取的信息
     */
    private void update() {
        System.out.println(name + " 收到推送消息： " + FrameJsonUtils.toJson(message));
    }
}
