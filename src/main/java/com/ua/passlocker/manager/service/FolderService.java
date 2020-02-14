package com.ua.passlocker.manager.service;


import com.ua.passlocker.manager.exceptions.GeneralNotExistException;
import com.ua.passlocker.manager.models.dto.FolderReq;
import com.ua.passlocker.manager.models.entity.Folders;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.repo.FolderRepository;
import com.ua.passlocker.manager.security.LocalContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderService {


    @Autowired
    private FolderRepository folderRepo;

    public Long createFolder(FolderReq folderReq) {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        if (ObjectUtils.isEmpty(folderReq.getParentId())) {
            return folderRepo.save(new Folders(folderReq.getName(), null, userDetails)).getId();
        } else {
            Folders folders = folderRepo.findById(folderReq.getParentId()).orElseThrow(() -> new GeneralNotExistException("Invalid Folder Id"));
            return folderRepo.save(new Folders(folderReq.getName(), folders, userDetails)).getId();

        }
    }

    public List<Folders> getAllFoldersForUser() {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        List<Folders> folders = folderRepo.findAllByUserDetails(userDetails);
        return folders.stream()
                .filter(folder -> ObjectUtils.isEmpty(folder.getParentId()))
                .collect(Collectors.toList());
    }

    public Folders fetchFolderById(Long id) {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        return folderRepo.findByIdAndUserDetails(id, userDetails).orElseThrow(() -> new GeneralNotExistException("Invalid Folder Id"));
    }

    public void updateFolder(FolderReq folderReq) {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        Folders folders = folderRepo.findByIdAndUserDetails(folderReq.getId(), userDetails).orElseThrow(() -> new GeneralNotExistException("Invalid Folder Id"));
        Folders parentFolder = folderRepo.findByIdAndUserDetails(folderReq.getParentId(), userDetails).orElseThrow(() -> new GeneralNotExistException("Invalid Folder Id"));
        folderRepo.save(folders.with(parentFolder));
    }
}
