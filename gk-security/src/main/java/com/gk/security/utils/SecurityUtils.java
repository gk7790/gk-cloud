package com.gk.security.utils;

import com.gk.security.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SecurityUtils {

    private SecurityUtils() {}


    /**
     * 获取当前用户ID
     */
    public static Long getUserId() {
        SysUser user = getCurrentUser();
        return user != null ? user.getId() : null;
    }

    /**
     * 获取当前用户名
     */
    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : null;
    }

    /**
     * 获取当前租户ID
     */
    public static Long getTenantId() {
        SysUser user = getCurrentUser();
        return user != null ? user.getTenantId() : null;
    }

    /**
     * 获取当前用户权限
     */
    public static List<String> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return Collections.emptyList();
        }

        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    /**
     * 检查是否有权限
     */
    public static boolean hasAuthority(String authority) {
        return getAuthorities().contains(authority);
    }

    /**
     * 检查是否有角色
     */
    public static boolean hasRole(String role) {
        return hasAuthority("ROLE_" + role);
    }

    /**
     * 是否是管理员
     */
    public static boolean isAdmin() {
        return hasRole("ADMIN");
    }

    /**
     * 获取当前登录用户
     */
    public static SysUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        if (authentication.getPrincipal() instanceof SysUser) {
            return (SysUser) authentication.getPrincipal();
        }

        return null;
    }
}
