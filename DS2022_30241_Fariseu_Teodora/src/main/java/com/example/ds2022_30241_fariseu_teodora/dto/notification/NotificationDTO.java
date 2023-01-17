package com.example.ds2022_30241_fariseu_teodora.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private String deviceID;
    private Double exceeded;
    private String message;
}
