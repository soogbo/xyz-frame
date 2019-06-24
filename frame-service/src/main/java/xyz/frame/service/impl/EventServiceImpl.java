package xyz.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import xyz.frame.service.EventService;
import xyz.frame.spring.event.DemoEvent;

/**
 * @author shisp
 * @date 2018-12-27 09:59:59
 */
@Service
public class EventServiceImpl implements EventService {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Override
    public void addEvent() {
        // 发布事件
        applicationContext.publishEvent(DemoEvent.instance("source", "msg", "email"));
    }

}
