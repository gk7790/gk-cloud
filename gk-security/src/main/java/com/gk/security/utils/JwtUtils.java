package com.gk.security.utils;

import com.gk.security.entity.SysUser;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JwtUtils {

    // JWT 签名密钥（生产环境应从配置读取）
    private static final String SECRET = "X8h9V4nK7eLpBz2G1qR0sY5mDf3wJt6uN4cE7aP1bQ8rFv9Z";
    private static final long expiration = 86400000L; // 24小时

    private static final SecretKey SECRET_KEY = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    // JwtEncoder
    private static final JwtEncoder encoder = new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));

    /**
     * 生成 JWT Token
     */
    public static String generateToken(SysUser user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        // 构建 claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("tenantId", user.getTenantId());
        claims.put("email", user.getEmail());
        claims.put("realName", user.getRealName());
        claims.put("authorities", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims) // 先设置 claims
                .setIssuer("gk-cloud")
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 验证并解析 JWT Token
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.warn("JWT Token 已过期: {}", token);
            throw new RuntimeException("Token 已过期");
        } catch (UnsupportedJwtException e) {
            log.warn("不支持的 JWT Token: {}", token);
            throw new RuntimeException("不支持的 Token 格式");
        } catch (MalformedJwtException e) {
            log.warn("JWT Token 格式错误: {}", token);
            throw new RuntimeException("Token 格式错误");
        } catch (SignatureException e) {
            log.warn("JWT Token 签名无效: {}", token);
            throw new RuntimeException("Token 签名无效");
        } catch (Exception e) {
            log.warn("JWT Token 解析失败: {}", e.getMessage());
            throw new RuntimeException("Token 解析失败");
        }
    }

    /**
     * 验证 Token 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 从 Token 中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 从 Token 中获取用户ID
     */
    public static Long getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("userId", Long.class);
    }

    /**
     * 从 Token 中获取权限列表
     */
    @SuppressWarnings("unchecked")
    public static java.util.List<String> getAuthoritiesFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("authorities", java.util.List.class);
    }

    /**
     * 刷新 Token（生成新Token）
     */
    public static String refreshToken(String token) {
        Claims claims = parseToken(token);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 检查 Token 是否过期
     */
    private boolean isTokenExpired(Jwt jwt) {
        return jwt.getExpiresAt() != null &&
                jwt.getExpiresAt().isBefore(Instant.now());
    }

    public static JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(getSecretKey()).build();
    }


    // 获取 SecretKey
    private static SecretKey getSecretKey() {
        return new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }
}
