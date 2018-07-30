package xyz.frame.configure.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * WebSocketController
 * 访问测试页面：http://localhost/websocket.html
 * 
 * @author shisp
 * @date 2018-7-30 16:59:52
 */
@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    /**
     * 接收客户端发送的消息：
     * 
     * 客户端发送位置1：需订阅/topic/sendTest才能收到此条发送
     * /app/sendTest
     * @MessageMapping("/sendTest")
     * 
     * 客户端发送位置2：需订阅/topic/subscribeTest才能收到此条发送
     * /app/sendTest
     * @MessageMapping("/sendTest")
     * @SendTo("/topic/subscribeTest")
     * 
     * @param message
     * @return
     */
    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sendDemo(ClientMessage message) {
        logger.info("接收到了信息" + message.getName());
        
        new Thread(() -> {  
            // 测试服务端推送信息，客户端应在5s后收到此条推送
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            templateTest();
        }).start();
        
        return new ServerMessage("你发送的消息为:" + message.getName());
    }

    /**
     * 客户端订阅的位置1：
     * /app/subscribeTest 
     * @SubscribeMapping("/subscribeTest")
     * 
     * 客户端订阅的位置2：
     * /topic/subscribeTest 
     * @SubscribeMapping("/subscribeTest") 
     * @SendTo("/topic/subscribeTest")
     * 
     * @return
     */
    @SubscribeMapping("/subscribeTest")
    public ServerMessage sub() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }


    /**
     * 服务端推送消息demo，（订阅位置，所推送的消息）
     */
    public void templateTest() {
        simpMessagingTemplate.convertAndSend("/topic/subscribeTest", new ServerMessage("服务器主动推的数据"));
    }
}