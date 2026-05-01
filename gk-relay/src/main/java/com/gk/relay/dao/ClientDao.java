package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.ClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay客户表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface ClientDao extends BaseDao<ClientEntity> {
    ClientEntity findByUsername(String username);
}