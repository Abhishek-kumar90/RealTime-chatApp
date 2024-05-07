package com.chat.chatApp.Config;

import com.chat.chatApp.chat.ChatMessage;
import com.chat.chatApp.chat.MeassageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.awt.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;


    @EventListener
    public void HandleEventDisconnectListener(

            SessionDisconnectEvent  event
    ){

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username  =(String) headerAccessor.getSessionAttributes().get("username");

        if(username != null){
            log.info("user Disconnected: {} ",username);

            var chatMessage = ChatMessage.builder()
                    .type(MeassageType.LEAVE)
                    .sender(username)
                    .build();

            messageTemplate.convertAndSend("/topic/public",chatMessage);
        }
    }

}
