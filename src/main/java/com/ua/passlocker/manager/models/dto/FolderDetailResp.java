package com.ua.passlocker.manager.models.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ua.passlocker.manager.models.FolderBreadCrumbs;
import com.ua.passlocker.manager.models.entity.Folders;
import com.ua.passlocker.manager.views.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FolderDetailResp {

    @JsonView(Views.VaultDetailView.class)
    private List<FolderBreadCrumbs> breadCrumbs;

    @JsonView(Views.VaultDetailView.class)
    private Folders folders;
}

