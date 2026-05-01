package com.gk.relay.dao;

import com.gk.common.core.dao.BaseDao;
import com.gk.relay.entity.RouteRuleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* sing-box路由规则表
*
* @author Lowen lowen@gmail.com
* @since 3.0 2026-04-30
*/
@Mapper
public interface RouteRuleDao extends BaseDao<RouteRuleEntity> {

    List<RouteRuleEntity> listActiveByPolicyId(Long policyId);
}