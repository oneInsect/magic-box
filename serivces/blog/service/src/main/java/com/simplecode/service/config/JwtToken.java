package com.simplecode.service.config;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 1L;

    private String token;

    private String expireAt;

    public JwtToken(String token) {
        this.token = token;
    }

    public JwtToken(String token, String expireAt) {
        this.token = token;
        this.expireAt = expireAt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
