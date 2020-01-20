package com.ua.passlocker.manager.models.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.ua.passlocker.manager.views.Views;
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
@Table(name = "folders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Folders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.FolderView.class)
    private Long id;

    @JsonView(Views.FolderView.class)
    private String name;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parentId")
    private Folders parentId;

    @ManyToOne
    @JoinColumn(name = "userDetailId", referencedColumnName = "id")
    private UserDetails userDetails;

    @JsonView(Views.FolderView.class)
    private Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "parentId", fetch = FetchType.EAGER)
    @JsonView(Views.FolderView.class)
    private Set<Folders> subFolders = new HashSet<>();

    public Folders(String name, Folders parentId, UserDetails userDetails) {
        this.name = name;
        this.parentId = parentId;
        this.userDetails = userDetails;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Folders with(Folders parentId) {
        this.parentId = parentId;
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
        return this;
    }
}
