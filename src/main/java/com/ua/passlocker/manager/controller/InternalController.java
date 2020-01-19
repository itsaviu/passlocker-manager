package com.ua.passlocker.manager.controller;


import com.ua.passlocker.manager.models.dto.UserDetailReq;
import com.ua.passlocker.manager.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/sync")
    public ResponseEntity syncData(@RequestBody UserDetailReq userDetailReq) {
        userDetailService.syncUsers(userDetailReq);
        return ResponseEntity.ok("synced...");
    }
}
