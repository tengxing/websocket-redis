package cn.yjxxclub.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Author: Starry.Teng
 * Email: tengxing7452@163.com
 * Date: 17-9-12
 * Time: 下午12:57
 * Describe: WebSocketConfig
 */
@Configuration
@EnableWebSocketMessageBroker
//通过EnableWebSocketMessageBroker 开启使用STOMP协议来传输基于代理(message broker)的消息,此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //配置消息代理(message broker) 设置消息连接请求的各种规范
        config.enableSimpleBroker("/topic");//客户端订阅地址的前缀信息
        config.setApplicationDestinationPrefixes("/app");//客户端给服务端发消息的地址的前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个名字为"gs-guide-websocket" 的endpoint,并指定 SockJS协议;添加一个服务端点，来接收客户端的连接
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
    }

}