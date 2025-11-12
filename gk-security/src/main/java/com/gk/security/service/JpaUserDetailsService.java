package com.gk.security.service;

import com.gk.security.dao.SecurityDao;
import com.gk.security.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final SecurityDao securityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: {}", username);
        Optional<SysUser> userOpt = securityDao.findByUsername(username);
//        validateUser(user);
        SysUser user = userOpt.get();

        log.info("User loaded successfully: {}, roles: {}", username, user.getAuthorities().size());
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

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }
}
