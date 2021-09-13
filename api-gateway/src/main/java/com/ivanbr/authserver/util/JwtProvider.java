package com.ivanbr.authserver.util;

import com.ivanbr.authserver.exception.JwtIsInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private static final String ROLES_KEY = "roles";

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expiration:6000000}")
    private Long expirationInMilliseconds;

    public String createToken(String username) {
        Claims claims = Jwts.claims();
        claims.setSubject(username);
        claims.put(ROLES_KEY, new HashMap<>());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean isTokenValid(String token) {
        Date now = new Date();
        return getClaims(token).getExpiration().after(now);
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (JwtException jwtException) {
            LOGGER.error("Jwt is invalid: {}", jwtException.getMessage());
            throw new JwtIsInvalidException("Jwt is invalid: "+jwtException.getMessage());
        }
    }
}
