package com.bsuir.herman.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**SOURCE = https://sysout.ru/chat-na-spring-boot-i-websocket/  **/

@Configuration
//Аннотация @Configuration необходима, как известно, в любом классе конфигурации Spring
@EnableWebSocketMessageBroker
//Аннотация @EnableWebSocketMessageBroker включает Websocket сервер. Обратите внимание,
// мы реализуем интерфейс WebSocketMessageBrokerConfigurer и переопределяем два из его default-методов.
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /*
    * В первом методе registerStompEndpoints() мы регистрируем конечную точку,
    *  которую клиенты будут использовать, чтобы подключиться к нашему Websocket-серверу.
    *  SockJS – для браузеров, которые не поддерживают Websocket.
    * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();

    }

    /*
    * Во втором методе configureMessageBroker() мы настраиваем брокер сообщений,
    *  который будет использоваться для направления сообщений от одного клиента к другому.
    * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
        * В первой строке мы говорим, что сообщения, чей адрес (куда отправлены) начинается
        * с  “/app“, должны быть направлены в методы, занимающиеся обработкой сообщений.
        */
        registry.setApplicationDestinationPrefixes("/app");
        /*
        * Во второй строке мы говорим, что  сообщения, чей адрес начинается с  “/topic“,
        * должны быть направлены в брокер сообщений. Брокер перенаправляет сообщения всем клиентам, подписанным на тему.
        * */
        registry.enableSimpleBroker("/topic");
    }
}
