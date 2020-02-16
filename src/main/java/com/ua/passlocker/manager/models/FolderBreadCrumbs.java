package com.ua.passlocker.manager.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.ua.passlocker.manager.views.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FolderBreadCrumbs {

    @JsonView(Views.VaultDetailView.class)
    private Long id;

    @JsonView(Views.VaultDetailView.class)
    private String name;
}
