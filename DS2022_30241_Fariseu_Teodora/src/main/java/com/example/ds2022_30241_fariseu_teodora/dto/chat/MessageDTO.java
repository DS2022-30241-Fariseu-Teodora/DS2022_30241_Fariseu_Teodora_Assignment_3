package com.example.ds2022_30241_fariseu_teodora.dto.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    String id;
    String username;
    String message;
    Boolean read;
    @Setter(AccessLevel.NONE)
    String sentDate;
    Boolean isFromMe;
}
