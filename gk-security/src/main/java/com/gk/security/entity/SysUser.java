package com.gk.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class SysUser implements UserDetails {

    private Long  id;
    private Long tenantId;
    private Long deptId;
    private String username;
    private String nickName;
    private String password;
    private String email;
    private String avatar;
    private Integer superAdmin;
    private Integer status;
    private String realName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public boolean isSuperAdmin() {
        return superAdmin == 1;
    }
}
