package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.RoutePolicyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* sing-box路由策略表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface RoutePolicyDao extends BaseDao<RoutePolicyEntity> {
	
}