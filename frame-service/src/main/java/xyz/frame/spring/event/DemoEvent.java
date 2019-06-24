package xyz.frame.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 *spring 事件为bean 与 bean之间传递消息。一个bean处理完了希望其余一个接着处理.这时我们就需要其余的一个bean监听当前bean所发送的事件.

    spring事件使用步骤如下:
    
    1.先自定义事件：你的事件需要继承 ApplicationEvent
    
    2.定义事件监听器: 需要实现 ApplicationListener
    
    3.使用容器对事件进行发布
 */
public class DemoEvent extends ApplicationEvent{
    private static final long serialVersionUID = -7545669220314273469L;

    private String msg;
    
    private String email;

    public static DemoEvent instance(Object source,String msg,String email) {
        return new DemoEvent(source, msg, email);
    }
    
    public DemoEvent(Object source,String msg,String email) {
        super(source);
        this.msg=msg;
        this.email=email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "DemoEvent [msg=" + msg + ", email=" + email + "]";
    }
}