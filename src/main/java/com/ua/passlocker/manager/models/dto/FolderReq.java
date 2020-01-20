package com.ua.passlocker.manager.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FolderReq {

    private Long id;

    private String name;

    private Long parentId;

}
