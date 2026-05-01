package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.ClientNodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay客户节点授权表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-05-01
*/
@Mapper
public interface ClientNodeDao extends BaseDao<ClientNodeEntity> {
	
}