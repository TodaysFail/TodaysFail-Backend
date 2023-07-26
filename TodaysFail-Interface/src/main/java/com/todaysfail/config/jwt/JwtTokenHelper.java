package com.todaysfail.config.jwt;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.exception.ExpiredTokenException;
import com.todaysfail.common.exception.InvalidTokenException;
import com.todaysfail.common.exception.RefreshTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenHelper {
    @Value("${auth.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${auth.jwt.access-exp}")
    private Long jwtAccessExp;

    @Value("${auth.jwt.refresh-exp}")
    private Long jwtRefreshExp;

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    private String buildAccessToken(
            Long id, Date issuedAt, Date accessTokenExpiresIn, String role) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .claim(TOKEN_ROLE, role)
                .setExpiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    private String buildRefreshToken(Long id, Date issuedAt, Date accessTokenExpiresIn) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .setExpiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    public String generateAccessToken(Long id, String role) {
        final Date issuedAt = new Date();
        final Date accessTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtAccessExp * MILLI_TO_SECOND);

        return buildAccessToken(id, issuedAt, accessTokenExpiresIn, role);
    }

    public String generateRefreshToken(Long id) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtRefreshExp * MILLI_TO_SECOND);
        return buildRefreshToken(id, issuedAt, refreshTokenExpiresIn);
    }

    public boolean isAccessToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(ACCESS_TOKEN);
    }

    public boolean isRefreshToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(REFRESH_TOKEN);
    }

    public Long parseAccessToken(String token) {
        if (isAccessToken(token)) {
            Claims claims = getJws(token).getBody();
            return Long.parseLong(claims.getSubject());
        }
        throw new InvalidTokenException();
    }

    public Long parseRefreshToken(String token) {
        try {
            if (isRefreshToken(token)) {
                Claims claims = getJws(token).getBody();
                return Long.parseLong(claims.getSubject());
            }
        } catch (ExpiredTokenException e) {
            throw new RefreshTokenExpiredException();
        }
        throw new InvalidTokenException();
    }

    public Long getRefreshTokenTTlSecond() {
        return jwtRefreshExp;
    }
}
