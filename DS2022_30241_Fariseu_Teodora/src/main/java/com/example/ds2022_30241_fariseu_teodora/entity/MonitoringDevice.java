package com.example.ds2022_30241_fariseu_teodora.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class MonitoringDevice {
    @Id
    @Column(name="device_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    private DeviceModel deviceModel;
    @Column
    private String description;
    @Column
    private String address;
    @OneToMany(mappedBy = "sourceDevice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EnergyConsumption> registeredConsumption;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private SiteUser owner;
    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Notification> notifications;
}
