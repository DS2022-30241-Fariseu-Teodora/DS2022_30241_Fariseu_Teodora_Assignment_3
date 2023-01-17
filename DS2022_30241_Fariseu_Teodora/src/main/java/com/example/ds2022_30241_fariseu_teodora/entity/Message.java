package com.example.ds2022_30241_fariseu_teodora.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @Column(name="message_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @Column(nullable = false)
    private String message;
    @Column
    private Double sentDate;
    @Column
    private Boolean read;
    @ManyToOne
    @JoinColumn(name = "discussion_id",referencedColumnName = "chat_id")
    private Chat discussion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private SiteUser author;
}
