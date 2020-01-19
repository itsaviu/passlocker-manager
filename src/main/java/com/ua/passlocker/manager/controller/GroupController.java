package com.ua.passlocker.manager.controller;

import com.ua.passlocker.manager.models.dto.GroupReq;
import com.ua.passlocker.manager.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody GroupReq groupReq) {
        groupService.createGroup(groupReq);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity getGroups() {
        return ResponseEntity.ok(groupService.getAllGroupForUser());
    }

}
