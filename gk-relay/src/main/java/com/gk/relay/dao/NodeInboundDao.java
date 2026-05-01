package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.NodeInboundEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay节点入站配置表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-05-01
*/
@Mapper
public interface NodeInboundDao extends BaseDao<NodeInboundEntity> {
	
}