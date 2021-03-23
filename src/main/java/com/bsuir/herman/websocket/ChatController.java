package com.bsuir.herman.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    //В классе ChatController есть методы, отвечающие за получение сообщения от одного клиента и трансляцию его всем остальным.

    /*
     * в конфигурации мы указали, что все сообщения от клиентов, направленные по адресу, начинающемуся с /app,
     *  будут перенаправлены в соответствующие методы. Имелись в виду как раз методы, аннотированные @MessageMapping.
     * */

    /*сообщение, направленное по адресу /app/chat/{to} будет перенаправлено в метод sendMessage().*/
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
