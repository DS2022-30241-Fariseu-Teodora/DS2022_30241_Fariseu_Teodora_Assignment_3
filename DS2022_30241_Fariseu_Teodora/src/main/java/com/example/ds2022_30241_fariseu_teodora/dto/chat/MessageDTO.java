package com.example.ds2022_30241_fariseu_teodora.dto.chat;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    String id;
    String username;
    String message;
    Boolean read;
    Double sentDate;
    String senderId;
    List<String> whoSaw;
}
