package com.ua.passlocker.manager.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String username;

    private String emailId;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "userDetailId")
    @JsonIgnore
    private List<Vault> vaults;

    public UserDetails(Long userId, String username, String emailId) {
        this.userId = userId;
        this.username = username;
        this.emailId = emailId;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
