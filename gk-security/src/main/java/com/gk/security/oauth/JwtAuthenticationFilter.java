package com.gk.security.oauth;

import com.gk.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取 Token
        String jwt = getJwtFromRequest(request);
        // 验证 Token
        if (StringUtils.isNotBlank(jwt) && JwtUtils.validateToken(jwt)) {
            try {
                // 从 Token 中获取用户名
                String username = JwtUtils.getUsernameFromToken(jwt);
                // 确保用户未认证且用户名有效
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 加载用户信息
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 从 Token 中获取权限信息
                    List<SimpleGrantedAuthority> authorities = getAuthoritiesFromToken(jwt);

                    // 创建认证对象
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    // 设置认证详情
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 设置到 SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    log.debug("JWT 认证成功: {}", username);
                }
            } catch (Exception e) {
                log.error("JWT 认证失败: {}", e.getMessage());
                // 清除 SecurityContext
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求中提取 JWT Token
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        // 1. 从 Authorization Header 获取
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // 2. 从 URL 参数获取（可选）
        String tokenParam = request.getParameter("token");
        if (StringUtils.isNotBlank(tokenParam)) {
            return tokenParam;
        }

        return null;
    }

    /**
     * 从 Token 中提取权限信息
     */
    @SuppressWarnings("unchecked")
    private List<SimpleGrantedAuthority> getAuthoritiesFromToken(String token) {
        try {
            List<String> authorities = JwtUtils.getAuthoritiesFromToken(token);
            if (authorities != null) {
                return authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.warn("从 Token 中提取权限失败: {}", e.getMessage());
        }
        return Collections.emptyList();
    }
}
