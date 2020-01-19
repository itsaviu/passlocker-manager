package com.ua.passlocker.manager.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Groups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parentId")
    @JsonIgnore
    private Groups parentId;

    @ManyToOne
    @JoinColumn(name = "userDetailId", referencedColumnName = "id")
    private UserDetails userDetails;

    private Timestamp createdAt;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
    @OneToMany(mappedBy = "parentId", fetch = FetchType.EAGER)
    private Set<Groups> childGroup = new HashSet<>();

    public Groups(String name, Groups parentId, UserDetails userDetails) {
        this.name = name;
        this.parentId = parentId;
        this.userDetails = userDetails;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}