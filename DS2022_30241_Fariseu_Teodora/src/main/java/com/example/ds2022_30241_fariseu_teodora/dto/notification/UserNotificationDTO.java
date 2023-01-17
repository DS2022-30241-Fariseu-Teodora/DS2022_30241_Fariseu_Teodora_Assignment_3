package com.example.ds2022_30241_fariseu_teodora.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationDTO {
    private String message;
    private String timestamp;
    private String deviceId;
    private String location;
}
