package com.example.ds2022_30241_fariseu_teodora.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewMessageDTO {
    String message;
    Double sentDate;
    String userId;
    String chatId;
}
