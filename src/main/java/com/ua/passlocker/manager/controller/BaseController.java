package com.ua.passlocker.manager.controller;

import com.ua.passlocker.manager.models.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseController {

    @ExceptionHandler(value = {Exception.class})
    public ErrorResponse exception(Exception ex) {
        log.error(ex.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong !");
    }

}
