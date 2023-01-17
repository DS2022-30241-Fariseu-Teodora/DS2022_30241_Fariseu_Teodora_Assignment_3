package com.example.ds2022_30241_fariseu_teodora.dto.consumption;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumptionDTO {
    protected String label;
    protected Double amount;
}
