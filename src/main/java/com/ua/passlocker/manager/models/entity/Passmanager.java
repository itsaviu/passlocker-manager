package com.ua.passlocker.manager.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "passmanager")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Passmanager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "userId")
    private UserDetails userDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Groups groupId;

    private String name;

    private String login;

    private String credentials;

    private String url;

    private String passType;

    private String notes;

    private Timestamp createdAt;

    public Passmanager(String username, UserDetails userDetailId, String name, String login, String credentials, String url, String passType, String notes) {
        this.username = username;
        this.userDetailId = userDetailId;
        this.name = name;
        this.login = login;
        this.credentials = credentials;
        this.url = url;
        this.passType = passType;
        this.notes = notes;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
