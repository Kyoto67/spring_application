package com.ifmo.cs.kyoto.alaba4.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWT {

    @Value("${com.ifmo.cs.kyoto.jwt.secret-word}")
    private String secret;

    public String generateToken(String username){
        return Jwts.builder()
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000))
                .setSubject(username).signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
