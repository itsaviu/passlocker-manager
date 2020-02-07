package com.ua.passlocker.manager.service;


import com.ua.passlocker.manager.exceptions.GeneralNotExistException;
import com.ua.passlocker.manager.models.PassType;
import com.ua.passlocker.manager.models.dto.VaultRequest;
import com.ua.passlocker.manager.models.entity.Folders;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.models.entity.Vault;
import com.ua.passlocker.manager.repo.FolderRepository;
import com.ua.passlocker.manager.repo.VaultRepository;
import com.ua.passlocker.manager.security.LocalContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaultService {

    @Autowired
    private VaultRepository vaultRepo;

    @Autowired
    private FolderRepository folderRepository;

    public void storeDetails(VaultRequest vaultRequest) {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        Folders folders = folderRepository.findByIdAndUserDetails(vaultRequest.getFolderId(), userDetails).orElseThrow(() -> new GeneralNotExistException("Folder id does not exist for user"));
        vaultRepo.save(new Vault(userDetails, folders, vaultRequest.getName(), vaultRequest.getLogin(), vaultRequest.getCredentials(), vaultRequest.getUrl(), PassType.DEFAULT, vaultRequest.getNotes()));
    }


    public void updateVaultDetails(VaultRequest vaultRequest) {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        Folders folders = folderRepository.findByIdAndUserDetails(vaultRequest.getFolderId(), userDetails).orElseThrow(() -> new GeneralNotExistException("Folder id does not exist for user"));
        Vault vault = vaultRepo.findByIdAndUserDetailId(vaultRequest.getId(), userDetails).orElseThrow(() -> new GeneralNotExistException("Vault Detail not available"));
        vaultRepo.save(vault.with(folders,vaultRequest.getName(), vaultRequest.getLogin(), vaultRequest.getCredentials(), vaultRequest.getUrl(), vaultRequest.getNotes()));
    }

    public List<Vault> fetchDetailsFromVault(Long folderId) {
        UserDetails userDetails = LocalContextHolder.getContextHolder().getUserDetails();
        Folders folders = folderRepository.findByIdAndUserDetails(folderId, userDetails).orElseThrow(() -> new GeneralNotExistException("Folder id does not exist for user"));
        return folders.getVaultList();
    }

}
