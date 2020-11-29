package com.ivanbr.authserver.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtIsInvalidException extends AuthenticationException {
    public JwtIsInvalidException(String msg) {
        super(msg);
    }
}
