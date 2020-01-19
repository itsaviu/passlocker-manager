package com.ua.passlocker.manager.service;


import com.ua.passlocker.manager.models.dto.GroupReq;
import com.ua.passlocker.manager.models.entity.Groups;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.repo.GroupRepository;
import com.ua.passlocker.manager.security.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {


    @Autowired
    private GroupRepository groupRepo;

    public void createGroup(GroupReq groupReq) {
        UserDetails userDetails = SecurityContextHolder.getContextHolder().getUserDetails();
        if (ObjectUtils.isEmpty(groupReq.getParentId())) {
            groupRepo.save(new Groups(groupReq.getName(), null, userDetails));
        } else {
            Groups group = groupRepo.findById(groupReq.getParentId()).orElseThrow(() -> new RuntimeException("Invalid Group Id"));
            groupRepo.save(new Groups(groupReq.getName(), group, userDetails));

        }
    }

    public List<Groups> getAllGroupForUser() {
        UserDetails userDetails = SecurityContextHolder.getContextHolder().getUserDetails();
        List<Groups> groups = groupRepo.findAllByUserDetailsSelfJoin(userDetails);
        return groups.stream()
                .filter(group -> ObjectUtils.isEmpty(group.getParentId()))
                .collect(Collectors.toList());
    }
}
