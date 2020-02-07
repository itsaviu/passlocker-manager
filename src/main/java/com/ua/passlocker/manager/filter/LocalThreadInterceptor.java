package com.ua.passlocker.manager.filter;

import com.ua.passlocker.manager.exceptions.UserNotExistException;
import com.ua.passlocker.manager.models.ContextHolder;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.security.LocalContextHolder;
import com.ua.passlocker.manager.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ua.passlocker.authorizer.security.AppContextHolder.getThreadLocal;

@Component
@Slf4j
public class LocalThreadInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Updating User Details");
        UserDetails userDetails = userDetailService.loadUserByEmailId(getThreadLocal()).orElseThrow(() -> new UserNotExistException("User not exist"));
        LocalContextHolder.setContextHolder(new ContextHolder(userDetails));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LocalContextHolder.removeContextHolder();
    }
}
