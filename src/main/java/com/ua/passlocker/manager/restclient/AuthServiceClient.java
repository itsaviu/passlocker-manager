package com.ua.passlocker.manager.restclient;

import com.ua.passlocker.manager.models.dto.UserDetailReq;
import com.ua.passlocker.manager.models.dto.UserDetailResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "authService", url = "${auth.url}")
public interface AuthServiceClient {

    @RequestMapping(value = "/auth/users", method = RequestMethod.POST, headers = MediaType.APPLICATION_JSON_VALUE)
    List<UserDetailResp> fetchUserDetail(@RequestBody UserDetailReq userDetailReq);

}
