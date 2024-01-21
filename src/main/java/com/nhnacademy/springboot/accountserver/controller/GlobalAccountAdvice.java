package com.nhnacademy.springboot.accountserver.controller;


import com.nhnacademy.springboot.accountserver.domain.AccountIdConflictException;
import com.nhnacademy.springboot.accountserver.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAccountAdvice {
    @ExceptionHandler(AccountIdConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleException(AccountIdConflictException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
