package com.cosmos.userservice.util;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

@Slf4j
public class TokenUtil {
    @Value("${token.secret}")
    private static String secretKey;

    public static String getUserIdByToken(HttpHeaders headers) {
        log.info("Token Util Start!");

        String token = headers.get("Authorization").get(0); // token 정보
        String jwt = token.replace("Bearer ", "");


        String userId = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().getSubject();

        log.info("userId : " + userId);

        log.info("Token Util End!");

        return userId;
    }
}