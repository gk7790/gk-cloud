package com.gk.common.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements Serializable {
    private Long userId;
    private String username;
    private String realName;
    private String email;
    private Boolean isAdmin;
    private Long deptId;
    private List<String> roles;
    private List<String> authCode;
}