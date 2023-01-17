package com.example.ds2022_30241_fariseu_teodora.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Setter
@Getter
public class EnergyConsumption {
    @Id
    @Column(name="entry_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @CreationTimestamp
    private LocalDateTime timestamp;
    @Column
    private Double valueConsumed;
    @ManyToOne
    @JoinColumn(name = "device_id",referencedColumnName = "device_id")
    private MonitoringDevice sourceDevice;
}
