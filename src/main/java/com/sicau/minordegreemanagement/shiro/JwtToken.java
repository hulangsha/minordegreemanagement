package com.sicau.minordegreemanagement.shiro;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * 实现shiro的AuthenticationToken接口的类JwtToken
 *
 * @author xiaosongyue
 * @date 2021/01/21 15:41:20
 */
public class JwtToken implements AuthenticationToken{

    private String token;

    public JwtToken(String token) {
        this.token = token;
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

