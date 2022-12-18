package com.example.namhim.jwt;

import com.example.namhim.models.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24h

    @Value("${app.jwt.secret}")
    private String secretKey;

    public String GenerateAccessToken(AppUser appUser) {
        return Jwts.builder()
                .setSubject(appUser.getId() + "," + appUser.getEmail())
                .setIssuer("Mugiira")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
