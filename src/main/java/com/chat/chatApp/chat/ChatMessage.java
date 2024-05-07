package com.chat.chatApp.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatMessage {

    private String content;
    private String sender;

    private MeassageType type;

}
