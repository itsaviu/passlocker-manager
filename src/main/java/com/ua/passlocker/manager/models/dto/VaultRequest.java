package com.ua.passlocker.manager.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaultRequest {

    private Long id;

    private Long folderId;

    private String name;

    private String login;

    private String credentials;

    private String url;

    private String notes;


}
