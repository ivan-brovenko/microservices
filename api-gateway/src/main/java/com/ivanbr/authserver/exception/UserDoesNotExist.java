package com.ivanbr.authserver.exception;

import org.springframework.security.core.AuthenticationException;

public class UserDoesNotExist extends AuthenticationException {
    public UserDoesNotExist(String message) {
        super(message);
    }
}
