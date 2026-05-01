package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.RouteHttpClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* sing-box远程规则集HTTP Client配置表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface RouteHttpClientDao extends BaseDao<RouteHttpClientEntity> {
	
}