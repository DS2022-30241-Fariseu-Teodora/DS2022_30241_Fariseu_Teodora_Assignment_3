package com.example.ds2022_30241_fariseu_teodora.dto.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDTO {
    private String id;
    private String name;
    private String previewMessage;
    private Integer unreadMessages;
}
