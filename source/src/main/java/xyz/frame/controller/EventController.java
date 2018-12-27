package xyz.frame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.frame.service.EventService;
import xyz.frame.utils.ResponseResult;
import xyz.frame.utils.RestResultUtil;

/**
 * @Description spring事件测试
 * @author shisp
 * @date 2018年2月5日 下午7:22:48
 */
@RequestMapping("/event")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/addEvent")
    public ResponseResult<?> addEvent() {
        eventService.addEvent();
        return RestResultUtil.success();
    }

}
