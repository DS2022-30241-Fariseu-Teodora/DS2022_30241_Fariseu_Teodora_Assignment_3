package com.example.ds2022_30241_fariseu_teodora.dto.privileges;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomeOptionsDTO {
    private String optionName;
    private String description;
    private String redirectURL;
}
