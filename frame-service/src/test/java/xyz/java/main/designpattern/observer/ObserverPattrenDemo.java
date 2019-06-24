package xyz.java.main.designpattern.observer;

public class ObserverPattrenDemo {
    
    public static void main(String[] args) {
        Subject subject = new WechatSubjectImpl();
        
        Observer userZhang = new UserObserverImpl("ZhangSan");
        Observer userLi = new UserObserverImpl("LiSi");
        Observer userWang = new UserObserverImpl("WangWu");
        
        subject.registerObserver(userZhang);
        subject.registerObserver(userLi);
        subject.registerObserver(userWang);
        subject.setInfomationAndNotify("PHP是世界上最好用的语言！");
        System.out.println("----------------------------------------------");
        
        subject.removeObserver(userZhang);
        System.out.println("移除观察者：ZhangSan");
        
        subject.setInfomationAndNotify("JAVA是世界上最好用的语言！");
        
    }
}