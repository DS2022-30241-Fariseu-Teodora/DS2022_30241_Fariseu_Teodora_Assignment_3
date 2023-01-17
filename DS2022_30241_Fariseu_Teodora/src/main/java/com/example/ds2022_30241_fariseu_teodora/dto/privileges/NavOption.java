package com.example.ds2022_30241_fariseu_teodora.dto.privileges;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NavOption {
    private String name;
    private String link;
    private String onClick;
}
