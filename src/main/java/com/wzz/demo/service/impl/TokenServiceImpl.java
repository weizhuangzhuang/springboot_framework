package com.wzz.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wzz.demo.pojo.User;
import com.wzz.demo.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        Date start = new Date();
        //一小时有效时间
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date((currentTime));
        String token = "";
        token = JWT.create().withAudience(user.getUserId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
