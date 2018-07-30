package xyz.frame.configure.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * WebSocketStompConfig springboot websocket stomp配置
 * 
 * @author shisp
 * @date 2018-7-30 17:01:04
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // js创建WebSocket连接，http://localhost:80/webSocketServer
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称
        // 用户订阅主题的前缀
        // /topic 代表发布广播，即群发
        // /queue 代表点对点，即发指定用户
        registry.enableSimpleBroker("/queue", "/topic");

        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
        // 设置客户端请求前缀:请求(进入服务端的位置)与订阅(服务端sentTo的位置)
        // 例如客户端发送消息的目的地为/app/sendTest，则对应控制层@MessageMapping(“/sendTest”)
        registry.setApplicationDestinationPrefixes("/app");

        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/");
    }

}