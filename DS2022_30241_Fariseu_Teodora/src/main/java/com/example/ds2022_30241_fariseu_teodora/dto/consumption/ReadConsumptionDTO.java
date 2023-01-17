package com.example.ds2022_30241_fariseu_teodora.dto.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadConsumptionDTO {
    protected Double timestamp;
    protected String deviceId;
    @JsonProperty("reading")
    protected Double measurementValue;

    public Date timestampDate() {
        return new Date(timestamp.longValue());
    }

    public LocalDateTime timestampLocalDateTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.longValue()),
                TimeZone.getDefault().toZoneId());
    }

    public LocalDate timestampLocalDate() {
        return LocalDate.ofInstant(Instant.ofEpochMilli(timestamp.longValue()), ZoneId.systemDefault());
    }
}
