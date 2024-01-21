package com.nhnacademy.springboot.accountserver.domain;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class AccountIdConflictException extends RuntimeException {
    public AccountIdConflictException(String message) {
        super(message);
    }
}
