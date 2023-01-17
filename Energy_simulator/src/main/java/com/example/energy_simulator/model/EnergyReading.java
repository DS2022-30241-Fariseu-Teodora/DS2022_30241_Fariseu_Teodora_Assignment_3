package com.example.energy_simulator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnergyReading {
    private String deviceId;
    private Float reading;
    private Double timestamp;
}
