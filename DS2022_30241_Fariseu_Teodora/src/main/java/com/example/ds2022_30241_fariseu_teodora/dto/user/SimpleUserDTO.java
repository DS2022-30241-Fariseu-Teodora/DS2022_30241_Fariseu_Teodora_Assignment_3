package com.example.ds2022_30241_fariseu_teodora.dto.user;

import com.example.ds2022_30241_fariseu_teodora.dto.deviceModel.DeviceModelDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleUserDTO {
    private String id;
    private String username;
}
