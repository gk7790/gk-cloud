package com.gk.security.dao;

import com.gk.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SecurityDao {

    Optional<SysUser> findByUsername(String username);

    Optional<SysUser> findByEmail(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}