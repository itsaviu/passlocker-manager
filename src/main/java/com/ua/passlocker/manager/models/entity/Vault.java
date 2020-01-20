package com.ua.passlocker.manager.models.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.ua.passlocker.manager.models.PassType;
import com.ua.passlocker.manager.views.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "vault")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.VaultView.class)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDetailId", referencedColumnName = "userId")
    private UserDetails userDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folderId")
    private Folders folderId;

    @JsonView(Views.VaultView.class)
    private String name;

    @JsonView(Views.VaultView.class)
    private String login;

    @JsonView(Views.VaultView.class)
    private String credentials;

    @JsonView(Views.VaultView.class)
    private String url;

    @Enumerated(value = EnumType.STRING)
    private PassType passType;

    @JsonView(Views.VaultView.class)
    private String notes;

    @JsonView(Views.VaultView.class)
    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Vault(UserDetails userDetailId, Folders folders, String name, String login, String credentials, String url, PassType passType, String notes) {
        this.userDetailId = userDetailId;
        this.folderId = folders;
        this.name = name;
        this.login = login;
        this.credentials = credentials;
        this.url = url;
        this.passType = passType;
        this.notes = notes;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Vault with(Folders folders, String name, String login, String credentials, String url, String notes) {
        this.folderId = folders;
        this.name = name;
        this.login = login;
        this.credentials = credentials;
        this.url = url;
        this.notes = notes;
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
        return this;
    }

}
