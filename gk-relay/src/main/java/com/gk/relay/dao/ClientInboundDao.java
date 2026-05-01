package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.ClientInboundEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay客户本地入站配置表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface ClientInboundDao extends BaseDao<ClientInboundEntity> {
	
}