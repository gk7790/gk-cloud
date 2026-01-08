package com.gk.security.service;

import cn.hutool.core.util.StrUtil;
import com.gk.common.constant.Constant;
import com.gk.common.redis.RedisKeys;
import com.gk.common.redis.RedisUtils;
import com.gk.security.dao.SecurityDao;
import com.gk.security.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final SecurityDao securityDao;
    private final RedisUtils redisUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: {}", username);
        Optional<SysUser> userOpt = securityDao.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        SysUser user = userOpt.get();
        validateUser(user);

        Set<Long> dataScopeList = getDataScopeList(user.getId());
        user.setDeptIdList(dataScopeList);

        Set<String> roleAuth = getRoleAuthList(user.getId());
        user.setRoleAuthList(roleAuth);

        Set<String> permissions =  getUserPermissions(user.getId(), user.isSuperAdmin());
        user.setAuthorities(permissions.parallelStream().map(SimpleGrantedAuthority::new).toList());

        return user;
    }

    private void validateUser(SysUser user) {
        if (!user.isEnabled()) {
            throw new DisabledException("用户已被禁用");
        }
        if (!user.isAccountNonLocked()) {
            throw new LockedException("用户账户已被锁定");
        }
        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("用户账户已过期");
        }
        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("用户凭证已过期");
        }
    }

    /**
     * 获取用户对应的部门数据权限
     * @param userId  用户ID
     * @return        返回部门ID列表
     */
    public Set<String> getRoleAuthList(Long userId) {
        String redisKey = RedisKeys.getSysLonginKey(Constant.ADMIN, "role-auth:" + userId);
        redisUtils.get(redisKey);
        Set<String> idList = redisUtils.getSet(redisKey, String.class);
        if (ObjectUtils.isNotEmpty(idList)) {
            return idList;
        }
        Set<String> scopeList = securityDao.getRoleAuthList(userId);
        if (ObjectUtils.isNotEmpty(scopeList)) {
            redisUtils.addSet(redisKey, scopeList, TimeUnit.HOURS.toSeconds(5));
        }
        return scopeList;
    }

    public Set<String> getUserPermissions(Long userId, boolean isAdmin) {
        String redisKey = RedisKeys.getSysLonginKey(Constant.ADMIN, "permissions:" + userId);
        Set<String> permList = redisUtils.getSet(redisKey, String.class);
        if (CollectionUtils.isNotEmpty(permList)) {
            return permList;
        }
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if (isAdmin) {
            permissionsList = securityDao.getPermissionsList();
        } else {
            permissionsList = securityDao.getUserPermissionsList(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StrUtil.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        redisUtils.addSet(redisKey, Collections.singleton(permsSet), TimeUnit.HOURS.toSeconds(5));
        return permsSet;
    }

    /**
     * 获取用户对应的部门数据权限
     * @param userId  用户ID
     * @return        返回部门ID列表
     */
    public Set<Long> getDataScopeList(Long userId) {
        String redisKey = RedisKeys.getSysLonginKey(Constant.ADMIN, "dataScope:" + userId);
        redisUtils.get(redisKey);
        Set<Long> idList = redisUtils.getSet(redisKey, Long.class);
        if (ObjectUtils.isNotEmpty(idList)) {
            return idList;
        }
        Set<Long> scopeList = securityDao.getDataScopeList(userId);
        if (ObjectUtils.isNotEmpty(scopeList)) {
            redisUtils.addSet(redisKey, scopeList, TimeUnit.HOURS.toSeconds(5));
        }
        return scopeList;
    }
}
