package com.example.ds2022_30241_fariseu_teodora.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class SiteUser {
    @Id
    @Column(name="user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(unique = true,nullable = false,name = "e_mail")
    private String email;
    @Column(nullable = false,columnDefinition = "varchar(60)")
    private String password;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime joinedAt;
    @Column(nullable = false)
    private Role role;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MonitoringDevice> monitoringDevices;
    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Chat> discussions;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages;
    @ManyToMany(mappedBy = "seenBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> seenBy;
}
