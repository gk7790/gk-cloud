package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.GroupClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* Relay授权组客户关系表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface GroupClientDao extends BaseDao<GroupClientEntity> {
	
}