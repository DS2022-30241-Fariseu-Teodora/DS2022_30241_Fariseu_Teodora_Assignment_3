package com.example.ds2022_30241_fariseu_teodora.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TypingDTO {
    private String chatId;
    private Boolean isTyping;
}
