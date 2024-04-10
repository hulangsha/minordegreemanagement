package com.sicau.minordegreemanagement.common.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
@Component
public class JwtUtil {
    @Value("${system.expireTime}")
    private Integer expireTime;

    /**
     * 生成token
     *
     * @param userName
     * @param secret
     * @return
     */
    public String sign(String userName, String secret){

        try {
            Algorithm algorithm = Algorithm.HMAC256(userName + secret);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND,expireTime);
            Date time = calendar.getTime();
            return JWT.create().withClaim("userName",userName).withExpiresAt(time).sign(algorithm);
        } catch (Exception e) {
            System.out.println("获取token的值为空所报的异常:{}" + e);
            return null;
        }

    }

    /**
     * 从JWTToken中获取用户名
     * @param token
     * @return
     */
    public String getUserName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            String userName = jwt.getClaim("userName").asString();
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public Date getExpireTime(String token){
        return JWT.decode(token).getExpiresAt();
    }
}