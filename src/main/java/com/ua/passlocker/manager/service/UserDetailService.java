package com.ua.passlocker.manager.service;

import com.ua.passlocker.manager.models.dto.UserDetailReq;
import com.ua.passlocker.manager.models.dto.UserDetailResp;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.repo.UserDetailRepository;
import com.ua.passlocker.manager.restclient.AuthServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepo;

    @Autowired
    private AuthServiceClient authServiceClient;

    public Optional<UserDetails> loadUserByEmailId(String emailId) {
        return userDetailRepo.findByEmailId(emailId);
    }

    public void syncUsers(UserDetailReq userDetailReq) {
        List<UserDetailResp> userDetails = authServiceClient.fetchUserDetail(userDetailReq);
        List<UserDetails> userDetailsList = userDetails.stream()
                .map(userDetailResp -> new UserDetails(userDetailResp.getId(), userDetailResp.getUsername(), userDetailResp.getEmailId()))
                .collect(Collectors.toList());
        userDetailRepo.saveAll(userDetailsList);
    }
}
