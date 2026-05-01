package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.GroupNodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay授权组节点关系表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface GroupNodeDao extends BaseDao<GroupNodeEntity> {
	
}