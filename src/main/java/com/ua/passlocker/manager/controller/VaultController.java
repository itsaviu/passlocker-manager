package com.ua.passlocker.manager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ua.passlocker.manager.models.dto.VaultRequest;
import com.ua.passlocker.manager.service.VaultService;
import com.ua.passlocker.manager.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vault")
public class VaultController {

    @Autowired
    private VaultService vaultService;

    @PostMapping("/store")
    public ResponseEntity storeDetails(@RequestBody VaultRequest vaultRequest) {
        vaultService.storeDetails(vaultRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateVault(@RequestBody VaultRequest vaultRequest) {
        vaultService.updateVaultDetails(vaultRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/credentials")
    @JsonView(Views.VaultView.class)
    public ResponseEntity getDetails(@RequestParam("folderId") Long folderId) {
        return ResponseEntity.ok(vaultService.fetchDetailsFromVault(folderId));
    }

}
