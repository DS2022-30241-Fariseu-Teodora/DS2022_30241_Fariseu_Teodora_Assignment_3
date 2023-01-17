package com.example.ds2022_30241_fariseu_teodora.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class DeviceModel {
    @Id
    @Column(name="model_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @Column
    private String modelName;
    @Column
    private String serialNumber;
    @Column
    private Float maxDeviceConsumption;
    @OneToMany(mappedBy = "deviceModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MonitoringDevice> deviceInstances;
}
