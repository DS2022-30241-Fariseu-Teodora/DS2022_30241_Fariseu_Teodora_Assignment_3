package com.example.ds2022_30241_fariseu_teodora.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class EditProfileDTO {
    private String id;
    private String username;
    private String email;
}
