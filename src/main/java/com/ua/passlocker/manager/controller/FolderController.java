package com.ua.passlocker.manager.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ua.passlocker.manager.models.dto.FolderReq;
import com.ua.passlocker.manager.service.FolderService;
import com.ua.passlocker.manager.views.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @PostMapping("/create")
    public ResponseEntity createFolder(@RequestBody FolderReq folderReq) {
        return ResponseEntity.ok(new HashMap<String, Long>() {{
            put("id", folderService.createFolder(folderReq));
        }});
    }

    @PostMapping("/update")
    public ResponseEntity updateFolder(@RequestBody FolderReq folderReq) {
        folderService.updateFolder(folderReq);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/lists")
    @JsonView(Views.FolderView.class)
    public ResponseEntity getFolders() {
        return ResponseEntity.ok(folderService.getAllFoldersForUser());
    }

    @GetMapping("/{id}")
    @JsonView(Views.VaultDetailView.class)
    public ResponseEntity getFolderDetail(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.fetchFolderById(id));
    }

}
